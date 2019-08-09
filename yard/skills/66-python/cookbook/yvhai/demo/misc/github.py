import os
from collections import namedtuple
from github3 import login

GhAuth = namedtuple("GhAuth", ["username", "password"])


def main():
    # env_user_passwd = os.getenv("GH_AUTH", ":").split(":")
    gh_auth = GhAuth(*os.getenv("GH_AUTH", ":").split(":"))
    gh = login(gh_auth.username, password=gh_auth.password)

    me = gh.me()
    print(me.name)
    print(me.login)
    print(me.followers_count)

    for f in gh.followers():
        f_name = str(f)
        gh2 = gh.user(str(f))
        print(f_name, gh2.name, gh2.login, gh2.followers_count)

    kennethreitz = gh.user('kennethreitz')
    # <User [kennethreitz:Kenneth Reitz]>

    print(kennethreitz.name)
    print(kennethreitz.login)
    print(kennethreitz.followers_count)

    followers = [str(f) for f in gh.followers('kennethreitz')]
    print(followers)


if __name__ == '__main__':
    main()
    # print("hallo")
