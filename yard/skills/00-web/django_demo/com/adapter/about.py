
class GlobalSetting(object):
    # 设置base_site.html的Title
    site_title = "好好学习"
    # 设置base_site.html的Footer
    site_footer = "天天向上"


def init_about(adm, views):
    adm.site.register(views.CommAdminView, GlobalSetting)
