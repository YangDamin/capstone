from django.db import models

# Create your models here.
class Fuser(models.Model):
    user_id = models.CharField(max_length=32, verbose_name="아이디")
    user_name = models.CharField(max_length=32, verbose_name="카페이름")
    user_email = models.CharField(max_length=64, verbose_name="이메일")
    password = models.CharField(max_length=64, verbose_name="비밀번호")
    register_date = models.DateField(auto_now_add=True, verbose_name="가입날짜")

    def __str__(self):
        return self.user_id

    class Meta:
        db_table = 'user'
        verbose_name = '사용자'
        verbose_name_plural = '사용자'
        
