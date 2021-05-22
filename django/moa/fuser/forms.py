from django import forms
from .models import Fuser
from django.contrib.auth.hashers import check_password

class LoginForm(forms.Form):
    user_id = forms.CharField(max_length=32, label="아이디",
        error_messages={
            'required' : "아이디를 입력하세요"
        }
    )
    password = forms.CharField(label="비밀번호", widget=forms.PasswordInput,
        error_messages={
            'required' : "비밀번호를 입력하세요"
        }
    )
    
    def clean(self):
        clean_data = super().clean()

        user_id = clean_data.get('user_id')
        password = clean_data.get('password')

        if user_id and password:
            fuser = Fuser.objects.get(user_id=user_id)

            if not check_password(password, fuser.password):
                self.add_error('password', '비밀번호가 틀렸습니다.')
            else:
                self.user_id = fuser.id