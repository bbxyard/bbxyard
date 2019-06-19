#!/usr/bin/env python3
# os demo

import os

from yvhai.demo.base import YHDemo


class OSDemo(YHDemo):
    def __init__(self):
        super(OSDemo, self).__init__('OS')

    # 场景-改变工作路径
    @staticmethod
    def change_work_path():
        _sec = OSDemo.mark_section('场景-改变工作路径')
        last_work_dir = os.getcwd()
        tmp_work_dir = '/tmp'
        print('[before] os.getcwd(): ', os.getcwd())
        os.chdir(tmp_work_dir)
        print('[after] os.getcwd(): ', os.getcwd())
        os.chdir(last_work_dir)
        print('[back] os.getcwd(): ', os.getcwd())

    # 场景-执行命令
    @staticmethod
    def invoke_out_command():
        _sec = OSDemo.mark_section('场景-执行命令')
        os.system('ls -lhrt /tmp')
        os.system('pwd')
        os.system('echo Current Date is: $(date +"%Y-%m-%d")')

    # 场景-读取环境变量
    @staticmethod
    def read_env_var():
        _sec = OSDemo.mark_section('场景-读取环境变量')
        print(' -- HOME: ', os.environ['HOME'])
        print(' -- USER: ', os.getenv('USER'))
        print(' -- PATH: ', os.getenv('PATH'))

    # 场景-解析文件路径信息
    @staticmethod
    def parse_path_info():
        _sec = OSDemo.mark_section('场景-解析文件路径信息')
        filepath = '/tmp/dir1/dir2/dirN/foobar-20190619.tar.bz2'
        print(' -- file_full_path: ', filepath)
        print(' -- os.path.basename(path): ', os.path.basename(filepath))
        print(' -- os.path.dirname(path): ', os.path.dirname(filepath))
        print(' -- os.path.abspath("1.txt"): ', os.path.abspath('1.txt'))
        print(' -- os.path.abspath("./1.txt"): ', os.path.abspath('./1.txt'))
        print(' -- os.path.abspath("../1.txt"): ', os.path.abspath('../1.txt'))

    # 场景-文件、目录信息
    @staticmethod
    def get_path_info():
        _sec = OSDemo.mark_section('场景-文件、目录信息')
        # 定性
        print(' -- os.path.isabs("/etc/passwd"): ', os.path.isabs('/etc/passwd'))
        print(' -- os.path.isdir("/etc/passwd"): ', os.path.isdir('/etc/passwd'))
        print(' -- os.path.islink("/etc/passwd"): ', os.path.islink('/etc/passwd'))
        print(' -- os.path.ismount("/etc/passwd"): ', os.path.ismount('/etc/passwd'))
        # 正向用例
        print(' -- os.path.isdir("/etc"): ', os.path.isdir('/etc'))
        print(' -- os.path.islink("/bin/sh"): ', os.path.islink('/bin/sh'))
        print(' -- os.path.ismount("/home"): ', os.path.ismount('/home'))
        print(' -- os.path.ismount("/run"): ', os.path.ismount('/run'))
        # 时间戳
        print(' -- os.path.getatime("/etc/passwd"): ', os.path.getatime('/etc/passwd'))
        print(' -- os.path.getctime("/etc/passwd"): ', os.path.getctime('/etc/passwd'))
        print(' -- os.path.getmtime("/etc/passwd"): ', os.path.getmtime('/etc/passwd'))

    @staticmethod
    def demo(args=[]):
        OSDemo.change_work_path()
        OSDemo.invoke_out_command()
        OSDemo.read_env_var()
        OSDemo.parse_path_info()
        OSDemo.get_path_info()
        pass
