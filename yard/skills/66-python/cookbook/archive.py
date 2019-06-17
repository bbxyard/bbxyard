#!/usr/bin/env python3
# 文件归档

import os
import shutil

class ArchiveMan:
  def info(self):
    print('archive types: ', shutil.get_archive_formats())

  def archive(self, dir, out_dir_path, out_file_basename):
    if not os.path.exists(out_dir_path):
      os.mkdir(out_dir_path)

    out_file_prefix = out_dir_path + '/' + out_file_basename
    shutil.make_archive(out_file_prefix, 'zip', dir)
    shutil.make_archive(out_file_prefix, 'tar', dir)
    shutil.make_archive(out_file_prefix, 'gztar', dir)
    shutil.make_archive(out_file_prefix, 'bztar', dir)

  def unpack(self, in_ar_file, out_dir):
    shutil.unpack_archive(in_ar_file, out_dir)


def main():
  ar = ArchiveMan()
  ar.archive('/usr/local/etc', 'out', 'usr-local-etc')
  ar.unpack('out/usr-local-etc.tar', 'out/usr-local-etc')
  print('done')


if __name__ == "__main__":
  main()

