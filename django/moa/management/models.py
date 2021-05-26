from django.db import models


# Create your models here.
class Manager(models.Model):
    cafe_name = models.CharField(max_length=64, verbose_name="카페", primary_key=True)
    cnt_stamp = models.IntegerField(verbose_name="쿠폰개수")
    cafe_explain = models.CharField(max_length=400, verbose_name="카페소개")
    
    def __str__(self):
        return self.cafe_name

    class Meta:
        db_table = "management"
        verbose_name = "등록 카페"
        verbose_name_plural = "등록 카페"