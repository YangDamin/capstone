from django.urls import path, include
from django.conf.urls import url
from . import views


urlpatterns = [
    path('aboutcoupon/', views.aboutcoupon, name='aboutcoupon'),
    path('coupon/', views.coupon),
    path('coupon_list/', views.coupon_list),
    path('present/<str:send_id>/<str:get_id>/', views.present)
]
