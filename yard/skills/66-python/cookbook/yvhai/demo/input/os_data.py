#!/usr/bin/env python3
# 操作系统数据源


class OSData:
    @staticmethod
    def parse_passwd_file(out_type='csv', file='/etc/passwd'):
        out = []
        with open(file, 'r') as fp:
            lines = fp.readlines()
            for ln in lines:
                ln = ln.strip('\n')
                row = ln.split(':')
                if len(row) < 7:
                    continue
                if out_type == 'csv':
                    out.append(row)
                elif out_type == 'dict':
                    item = {}
                    item['name'] = row[0]
                    item['uid'] = row[2]
                    item['gid'] = row[3]
                    out.append(item)
                # print(row)
        return out

