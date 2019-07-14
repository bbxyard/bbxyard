
# xadmin
from xadmin.plugins import xversion
import xadmin

xversion.register_models()
xadmin.autodiscover()


def get_xadmin_router(path):
    return [
        path('xadmin/', xadmin.site.urls)
    ]
