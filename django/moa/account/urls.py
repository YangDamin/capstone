from django.urls import path
from . import views

app_name = 'account'

urlpatterns = [
    path('login/', views.login),
    path('customer_list/',views.customer_list)
]