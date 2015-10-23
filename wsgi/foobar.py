def application(env, start_response):
    start_response('200 OK', [('Content-Type', 'text/html')])
    return '<h1>Hello %s!</h1>' % (env['QUERY_STRING'].split('=')[1])
