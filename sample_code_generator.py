import os
import sys

def CountStartSpaceNum(line):
  count = 0
  for c in line:
    if c == ' ':
      count = count + 1 
    else:
      if count < 4:
        raise Exception('count should not < 4!')
      return count - 4

def Stringlize(lines):
  ret = ''
  startSpaceCount = CountStartSpaceNum(lines[0])
  for i in range(0, len(lines)):
    l = lines[i]
    l = l.strip('\r\n')
    l = l[startSpaceCount:]
    l = l.replace('\"', '\\\"')
    s = "" if i == len(lines) -1 else "+"
    l = '\n\"' + l + '\\n\"' + s 
    ret = ret + l
  return ret

def RemoveHeader(lines):
  ret = []
  find_header_end = False
  for l in lines:
    if l.find('public void run') >= 0:
      find_header_end = True
    elif find_header_end:
      ret.append(l)
  return ret


def RemoveFooter(lines):
  ret = []
  end_lines = ['})','}']
  find_footer_end = False
  for i in range(len(lines)-1, -1, -1):
    l = lines[i]
    if len(end_lines) > 0 and l.find(end_lines[0]) >=0:
      end_lines.pop()
    else:
      ret.insert(0,l) 
  return ret

def ProcessRegisterBlock(lines):
  lines = RemoveHeader(lines)
  lines = RemoveFooter(lines)
  return Stringlize(lines)

def FindKey(line):
  start = line.find('Constants.')
  end = line.find(',',start)
  return line[start: end]

def CheckFile(file,codes):
  key = ""
  with open(file, 'r') as input:
    block = []
    find_registery = False
    for line in input:
      if line.find('registery.add(Constants.') >= 0:
        find_registery = True
        if len(block) > 0:
          code= ProcessRegisterBlock(block)
          codes[key] = code
          block = []
        block.append(line)
        key = FindKey(line)
      elif find_registery:
        block.append(line)
  return codes

#find all java files
codes = {}
for root,dirs, files in os.walk('.'):
  for file in files:
    if file.endswith('java') and root.find('api') >= 0:
        codes = CheckFile(os.path.join(root,file), codes)

dest = os.path.join('app','src','main','java','union','uc','com','rxjava_example','plugin')
if not os.path.exists(dest):
  os.path.makedirs(dest)

with open(os.path.join(dest,'SampleCode.java'),'w') as output:
  header = '''
package union.uc.com.rxjava_example.plugin;

import java.util.HashMap;
import java.util.Map;
import union.uc.com.rxjava_example.contants.Constants;

public class SampleCode{
  private Map<String, String> mCodes = new HashMap<>();
  public SampleCode(){
'''
  footer = '''  
  }
  public String get(String key){
    return mCodes.get(key);
  }
}
'''
  output.write(header)
  for key,code in codes.items():
    s = '\nmCodes.put(%s,%s);' % (key, code)
    output.write(s)
  output.write(footer)















