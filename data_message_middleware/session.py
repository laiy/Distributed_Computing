import tornado.httpserver
import tornado.ioloop
import tornado.web
import tornado.options
import os.path
import pymongo
import hashlib
import redis

from tornado.options import define, options
define("port", default=8000, help="run on the given port", type=int)

r = redis.StrictRedis(host='localhost', port=6379, db=0)

count = 0


def md5(str):
    m = hashlib.md5()
    m.update(str)
    return m.hexdigest()


class BaseHandler(tornado.web.RequestHandler):
    def get_current_user(self):
        return self.get_secure_cookie("myapp-session")


class LoginHandler(BaseHandler):
    def get(self):
        self.render('login.html')

    def post(self):
        user = r.hgetall('myapp-session')
        if user:
            if user["password"] == md5(self.get_argument("password")):
                session = str(count + 1)
                self.set_secure_cookie("myapp-session", session)
                self.redirect("/")
            else:
                self.redirect("/login")
        else:
            session = str(count + 1)
            user = {'username': self.get_argument("username"), 'password': md5(self.get_argument("password")), 'role': self.get_argument("role")}
            r.hmset(session, user)
            self.set_secure_cookie("myapp-session", session)
            self.redirect("/")


class WelcomeHandler(BaseHandler):
    @tornado.web.authenticated
    def get(self):
        self.render('index.html', user=self.current_user)


class LogoutHandler(BaseHandler):
    def get(self):
        self.clear_cookie("myapp-session")
        self.redirect("/")


class Application(tornado.web.Application):
    def __init__(self):
        handlers = [
            (r'/', WelcomeHandler),
            (r'/login', LoginHandler),
            (r'/logout', LogoutHandler),
            (r'/admins', AdminsHandler),
            (r'/users', UsersHandler),
            (r'/vips', VipsHandler),
            (r'/guests', GuestsHandler),
            (r'/error', ErrorHandler)
        ]

        settings = {
            "template_path": os.path.join(os.path.dirname(__file__), "templates"),
            "cookie_secret": "bZJc2sWbQLKos6GkHn/VB9oXwQt8S0R0kRvJ5/xJ89E=",
            "xsrf_cookies": True,
            "login_url": "/login"
        }

        conn = pymongo.MongoClient("localhost", 27017)
        self.db = conn["user"]
        tornado.web.Application.__init__(self, handlers, debug=True, **settings)


def role(arg):
    def _deco(func):
        def __deco(self, *args, **kwargs):
            coll = self.application.db.users
            username = self.get_secure_cookie("myapp-session")
            user = coll.find_one({"myapp-session": username})
            ok = False
            for role in arg:
                if (user["role"]) == role:
                    ok = True
                    break
            if ok:
                func(self, *args, **kwargs)
            else:
                self.redirect('/error')
        return __deco
    return _deco


class AdminsHandler(BaseHandler):
    @role(['admins'])
    def get(self):
        self.render('admins.html')


class UsersHandler(BaseHandler):
    @role(['users'])
    def get(self):
        self.render('users.html')


class VipsHandler(BaseHandler):
    @role(['vips'])
    def get(self):
        self.render('vips.html')


class GuestsHandler(BaseHandler):
    @role(['guests', 'users', 'vips', 'admins'])
    def get(self):
        self.render('guests.html')


class ErrorHandler(BaseHandler):
    def get(self):
        self.render('error.html')


if __name__ == "__main__":
    tornado.options.parse_command_line()

    http_server = tornado.httpserver.HTTPServer(Application())
    http_server.listen(options.port)
    tornado.ioloop.IOLoop.instance().start()
