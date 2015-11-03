import tornado.web
import tornado.wsgi


class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.write("Hello, world")

application = tornado.web.Application([
    (r"/", MainHandler),
])

wsgi_app = tornado.wsgi.WSGIAdapter(application)
