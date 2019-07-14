
class GlobalSetting(object):
    # 设置base_site.html的Title
    site_title = "好好学习"

    # 设置base_site.html的Footer
    site_footer = "天天向上"

    # 菜单折叠
    menu_style = "accordion"

    # 设置models的全局图标
    # global_search_models = ["V_UserInfo", "UserDistrict"]
    # global_models_icon = {
    #     "V_UserInfo": "glyphicon glyphicon-user",
    #     "UserDistrict": "fa fa-cloud"
    # }  # 设置models的全局图标


def init_about(adm, views):
    adm.site.register(views.CommAdminView, GlobalSetting)
