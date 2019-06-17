#!/usr/bin/env python3
# 文件归档

import os
import shutil

class ArchiveMan:
  def archive(self, dir, out_dir_path, out_file_basename):
    if not os.path.exists(out_dir_path):
      os.mkdir(out_dir_path)

    out_file_prefix = out_dir_path + '/' + out_file_basename
    shutil.make_archive(out_file_prefix, 'zip', dir)
    shutil.make_archive(out_file_prefix, 'tar', dir)
    # shutil.make_archive(out_file_prefix, 'tar.gz', dir)
    # shutil.make_archive(out_file_prefix, 'tar.bz2', dir)

  def unpack(self):
    pass


def main():
  ar = ArchiveMan()
  ar.archive('/usr/local/etc', 'out', 'usr-local-etc')
  print('done')


if __name__ == "__main__":
  main()

