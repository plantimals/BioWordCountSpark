#!/usr/bin/env python

import sys

count = {}
with open(sys.argv[1], 'r') as fh:
  for l in fh:
    if l[0] == "#":
      continue
    cols = l.strip().split("\t")
    ref = cols[3]
    var = cols[4]
    if len(ref) == 1 and len(var) == 1:
      key = "{0} -> {1}".format(ref,var)
      if count.get(key, -1) == -1:
        count[key] = 1
      else:
        count[key] += 1

for k in sorted(count.keys()):
  print "({0},{1})".format(k, count[k])
