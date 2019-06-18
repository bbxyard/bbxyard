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
                    # item['user'] = row[0]
                    item['uid'] = int(row[2])
                    item['gid'] = int(row[3])
                    item['home'] = row[5]
                    item['shell'] = row[6]
                    item['brief'] = row[4]
                    out.append(item)
                # print(row)
        return out

