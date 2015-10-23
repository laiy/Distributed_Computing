import tornado.httpserver
import tornado.ioloop
import tornado.options
import tornado.web

from tornado.options import define, options
define("port", default=8000, help="run on the given port", type=int)

dic = []


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
                dic.append((term, definition))
            count += 1
        tornado.web.Application.__init__(self, handlers, debug=True)


class WordHandler(tornado.web.RequestHandler):
    def get(self, word):
        word += '\n'
        for data in dic:
            if data[0] == word:
                self.write(str(data))
                return
        self.set_status(404)

    def post(self, word):
        definition = self.get_argument("definition") + '\n'
        word += '\n'
        for data in dic:
            if data[0] == word:
                dic.remove(data)
        word = word.encode()
        definition = definition.encode()
        data = (word, definition)
        dic.append(data)
        self.write(str(data))

if __name__ == "__main__":
    tornado.options.parse_command_line()
    http_server = tornado.httpserver.HTTPServer(Application())
    http_server.listen(options.port)
    try:
        tornado.ioloop.IOLoop.instance().start()
    except:
        file = open('dict.txt', 'w+')
        for data in dic:
            file.write(data[0])
            file.write(data[1])
            file.write('\n')
