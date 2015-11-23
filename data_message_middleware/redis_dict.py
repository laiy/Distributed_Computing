import tornado.httpserver
import tornado.ioloop
import tornado.options
import tornado.web
import redis

from tornado.options import define, options
define("port", default=8000, help="run on the given port", type=int)


class DictDao():
    def __init__(self):
        self.r = redis.StrictRedis(host='localhost', port=6379, db=0)
        self.r.flushdb()

    def create(self, key, value):
        self.r[key] = value

    def retrive(self, key):
        return self.r[key]

    def update(self, key, value):
        self.r[key] = value

    def delete(self, key):
        self.r.delete(key)

    def getAllKeys(self):
        return self.r.keys()


class Application(tornado.web.Application):
    def __init__(self):
        handlers = [(r"/(\w+)", WordHandler)]
        file = open('dict.txt', 'r')
        count = 0
        for data in file:
            if count % 3 == 0:
                term = data
            elif count % 3 == 1:
                definition = data
            else:
                dao.create(term, definition)
            count += 1
        tornado.web.Application.__init__(self, handlers, debug=True)


class WordHandler(tornado.web.RequestHandler):
    def get(self, word):
        word += '\n'
        if dao.retrive(word):
            self.write(str(dao.retrive(word)))
            return
        self.set_status(404)

    def post(self, word):
        definition = self.get_argument("definition") + '\n'
        word += '\n'
        word = word.encode()
        definition = definition.encode()
        dao.create(word, definition)

if __name__ == "__main__":
    dao = DictDao()
    tornado.options.parse_command_line()
    http_server = tornado.httpserver.HTTPServer(Application())
    http_server.listen(options.port)
    try:
        tornado.ioloop.IOLoop.instance().start()
    except:
        file = open('dict.txt', 'w+')
        keys = dao.getAllKeys()
        print keys
        for key in keys:
            file.write(key)
            file.write(dao.retrive(key))
            file.write('\n')
