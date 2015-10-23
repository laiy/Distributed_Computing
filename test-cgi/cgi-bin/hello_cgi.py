#!/usr/bin/env python

import cgi

args = cgi.FieldStorage()
print("")
print('Hello ' + args.getvalue('user') + '!')
