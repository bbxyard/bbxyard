#!/usr/bin/env python3
#

import pandas as pd
from yvhai.demo.input.os_data import OSData


def write_csv_by_pandas(mat, outfile):
    names, uids, gids = [], [], []
    num = 0
    for row in mat:
        print(row, len(row), num)
        num += 1
        # if len(row) >= 7:
        names.append(row[0])
        uids.append(row[2])
        gids.append(row[3])
    # 组装数据
    _data = pd.DataFrame()
    _data['用户名'] = names
    _data['用户id'] = uids
    _data['组id'] = gids
    # # 写入文件
    pd.concat([_data]).reset_index(drop=True).to_csv(outfile, index=False, encoding='utf-8')


def write_xlsx_by_openpyxl(mat, outfile):
    from openpyxl import Workbook
    wb = Workbook()
    ws1 = wb.active
    ws1.title = 'passwd'

    ws1['A1'] = '用户名'
    ws1['B1'] = '用户id'
    ws1['C1'] = '组id'

    row_cnt = len(mat)
    i = 1
    for row in mat:
        ws1['A%s' % (i + 1)] = row[0]
        ws1['B%s' % (i + 1)] = row[2]
        ws1['C%s' % (i + 1)] = row[3]
        i += 1
    wb.save(outfile)


def main():
    mat = OSData.parse_passwd_file()
    write_csv_by_pandas(mat, 'out/etc-passwd.csv')
    write_xlsx_by_openpyxl(mat, 'out/etc-passwd.xlsx')


if __name__ == "__main__":
    main()
