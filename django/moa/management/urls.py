from django.urls import path, include
from django.conf.urls import url
from . import views


urlpatterns = [
    url(r'^logout/$', views.logout, name='logout'),
    url(r'^aboutcoupon/$', views.aboutcoupon, name='aboutcoupon'),
    url(r'^aboutcafe/$', views.aboutcafe, name='aboutcafe'),


]
