import os
import sys

def ProcessRegisterBlock(lines):
  if len(lines) == 0:
    return None
  mb = []
  for line in lines:
    line = line.strip('\r\n ,);')
    line = line.replace('\"','');
    if len(line) < 10 or not line.startswith('http'):
      continue
    
    mb.append(line)
  return mb


def FindKey(line):
  start = line.find('Constants.')
  end = line.find(',',start)
  return line[start: end]

def Url2Id(url):
  start = url.rfind('/')
  if start < 0:
    return None
  return 'R.drawable.' + url[start + 1:].replace('.','_').lower()

codes = {}

dest = os.path.join('app','src','main','java','union','uc','com','rxjava_example','plugin')
if not os.path.exists(dest):
  os.path.makedirs(dest)

key = ""
with open(os.path.join(dest, 'MarbleDiagramPlugin.java'), 'r') as input:
  block = []
  find_add = False
  for line in input:
    if len(line.strip()) == 0:
      continue
    if line.find('add(Constants.') >= 0:
      find_add = True
      if len(block) > 0:
        code= ProcessRegisterBlock(block)
        codes[key] = code
        block = []
      key = FindKey(line)
      http_start = line.find('\"http')
      if http_start >= 0:
        http_end = line.find('\"', http_start + 1)
        if http_end >= 0:
          block.append(line[http_start:http_end])
    elif find_add:
      block.append(line)
  if find_add and len(block) > 0:
    code= ProcessRegisterBlock(block)
    codes[key] = code
    print key

with open(os.path.join(dest,'MarbleDiagram.java'),'w') as output:
  header = '''
package union.uc.com.rxjava_example.plugin;

import java.util.HashMap;
import java.util.Map;
import union.uc.com.rxjava_example.contants.Constants;
import union.uc.com.rxjava_example.R;
public class MarbleDiagram{
  private Map<String, Integer[]> mCodes = new HashMap<>();
  public MarbleDiagram(){
'''
  footer = '''  
  }
  public Integer[] get(String key){
    return mCodes.get(key);
  }
  private void add(String key, Integer... urls) {
    mCodes.put(key, urls);
  } 
}
'''
  output.write(header)
  for key,code in codes.items():
    if code == None:
      continue
    s = ""
    if len(code) == 1:
      code0 = Url2Id(code[0])
      s = '\nadd(%s,%s);' % (key, code0)
    elif len(code) == 2:
      code0 = Url2Id(code[0])
      code1 = Url2Id(code[1])
      s = '\nadd(%s,%s,%s);' % (key, code0, code1)
    elif len(code) == 3:
      code0 = Url2Id(code[0])
      code1 = Url2Id(code[1])
      code2 = Url2Id(code[2])
      s = '\nadd(%s,%s,%s,%s);' % (key, code0, code1, code2)
    elif len(code) == 4:
      code0 = Url2Id(code[0])
      code1 = Url2Id(code[1])
      code2 = Url2Id(code[2])
      code3 = Url2Id(code[3])
      s = '\nadd(%s,%s,%s,%s,%s);' % (key, code0, code1, code2, code3)
    output.write(s)
  output.write(footer)
#downlo all images
dir_drawable = os.path.join('app', 'src', 'main', 'res', 'drawable')
for key,code in codes.items():
  if code != None:
    for c in code:
      print c
      os.system('wget %s -P imgs' % (c ))

for root, dirs, files in os.walk('imgs'):
  for file in files:
      src = os.path.join(root,file)
      dest = os.path.join(dir_drawable, file.lower().replace('.', '_') + '.png')
      os.rename(src, dest)
