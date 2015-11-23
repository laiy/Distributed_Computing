#!usr/bin/env python

import redis

r = redis.StrictRedis(host='localhost', port=6379, db=0)
r.set('foo', 'bar')

print(r.get('foo'))

r.delete('foo')

print(r.dbsize())

r['test'] = 'ok!'

r.save()

r.flushdb()

print(r.keys())

r.set('bing', 'baz')

pipe = r.pipeline()

pipe.set('foo', 'bar')
pipe.get('bing')

print(pipe.execute())
