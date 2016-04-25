import os
import sys
import Image
dir_drawable = os.path.join('app', 'src', 'main', 'res', 'drawable')
for root, dirs, files in os.walk(dir_drawable):
  for file in files:
    if not file.endswith('.png'):
      continue
    src = os.path.join(root,file)
    img = Image.open(src)
    img = img.resize((400,200),Image.ANTIALIAS)
    img.save(src)
    del img

