from django.urls import path, include
from django.conf.urls import url
from . import views


urlpatterns = [
    url(r'^logout/$', views.logout),
    url(r'^aboutcafe/$', views.aboutcafe),
    path('cafe_list/', views.cafe_list)
]
