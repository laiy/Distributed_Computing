import tornado.httpserver
import tornado.ioloop
import tornado.web
import tornado.options
import os.path
import pymongo
import hashlib

from tornado.options import define, options
define("port", default=8000, help="run on the given port", type=int)


def md5(str):
    m = hashlib.md5()
    m.update(str)
    return m.hexdigest()


class BaseHandler(tornado.web.RequestHandler):
    def get_current_user(self):
        return self.get_secure_cookie("username")


class LoginHandler(BaseHandler):
    def get(self):
        self.render('login.html')

    def post(self):
        coll = self.application.db.users
        user = coll.find_one({"username": self.get_argument("username")})
        if user:
            print(user["password"])
            print(md5(self.get_argument("password")))
            if user["password"] == md5(self.get_argument("password")):
                self.set_secure_cookie("username", self.get_argument("username"))
                self.redirect("/")
            else:
                self.redirect("/login")
        else:
            coll.insert({"username": self.get_argument("username"), "password": md5(self.get_argument("password"))})
            self.set_secure_cookie("username", self.get_argument("username"))
            self.redirect("/")


class WelcomeHandler(BaseHandler):
    @tornado.web.authenticated
    def get(self):
        self.render('index.html', user=self.current_user)


class LogoutHandler(BaseHandler):
    def get(self):
        self.clear_cookie("username")
        self.redirect("/")


class Application(tornado.web.Application):
    def __init__(self):
        handlers = [
            (r'/', WelcomeHandler),
            (r'/login', LoginHandler),
            (r'/logout', LogoutHandler)
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


if __name__ == "__main__":
    tornado.options.parse_command_line()

    http_server = tornado.httpserver.HTTPServer(Application())
    http_server.listen(options.port)
    tornado.ioloop.IOLoop.instance().start()
