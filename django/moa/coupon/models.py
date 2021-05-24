from django.db import models
from moa.account.models import Customer
from moa.management.models import Management

class coupon(models.Model) :
    customer = models.ForeignKey('Customer',on_delete=models.CASCADE, verbose_name="유저 이름")

    store = models.ForeignKey('Management',on_delete=models.CASADE,verbose_name="카페 이름")
    
    current_cnt = models.PositiveIntegerField(default='0',verbose_name='현재 개수') 
    
     def __str__(self):
        return self.{(customer,store):current_cnt}

    class Meta:
        db_table = "coupon"
        verbose_name = "coupon"
        verbose_name_plural = "coupon"