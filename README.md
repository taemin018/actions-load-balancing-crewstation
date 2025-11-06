## 🔥 AWS EC2 2대 + NGINX 로드밸런싱 + GitHub Actions 자동 배포 🔥

### 📌 Overview

- EC2 2대 운영 & NGINX 로드밸런싱
- GitHub Actions 자동화 배포 파이프라인 구축
- 서버별 환경 변수 분리 및 동시 배포 처리

---

## 🎈 로드밸런싱 자동 배포 인프라 구축 

---

## 🔧 GitHub Actions 설정

### ✅ 1. Secrets 설정

| Key | Description |
|-----|---------------|
| EC2_USER | EC2 접속 유저 |
| EC2_KEY | PEM Key 내용 전체 |
| EC2_HOST1 |서버 #1 IP |
| EC2_HOST2 | 서버 #2 IP |
| EC2_HOST | Nginx Reverse Proxy Server |
|AWS_ACCESS_KEY_ID|  IAM 액세스 키 |
|AWS_REGION|  aws 지역명 |
|AWS_S3_BUCKET|  aws s3 버킷 이름 |
|AWS_SECRET_ACCESS_KEY| IAM 시크릿 키 |
|MAIL_PASSWORD| Gmail 비밀번호 |
|JWT_SECRET| openssl rand -base64 32의 결과 값 |
|KAKAO_CLIENT_ID| 카카오 rest api 키 |
|KAKAO_CLIENT_SECRET| 카카오 secret 키 |
|NAVER_CLIENT_ID| 네이버 Client ID |
|NAVER_CLIENT_SECRET| 네이버 Client Secret |
|SMS_ACCESS_KEY| coolsms access 키 |
|SMS_SECRET_KEY| coolsms secret 키 |


---

### ⚙️ 2. Workflow 파일 및 Dockerfile 파일 생성하기

| 파일 | 설명 |
|------|--------|
|`load_balancing_deploy.yml`| GitHub Actions 배포 설정|
|`Dockerfile`| Docker 빌드 설정| 

---

## 🌐 NGINX Load Balancer 설정

### 1. 설치
```bash
# 설치 명령어
 sudo apt install -y nginx

# 상태 확인
 sudo systemctl status nginx
 

```
### 2. 설정 파일 생성
```bash
# 파일 만들기 [이름은 자유롭게]
 sudo vim /etc/nginx/sites-available/[이름] 
```
### 3. 설정 작성
```nginx
upstream [이름] {
        least_conn;
        server <EC2-IP-1>:80;
        server <EC2-IP-2>:80;
}

server {
        listen 80;

        location / {
                proxy_pass http://[이름];
                proxy_set_header Host $host;
                proxy_set_header X-Real-IP $remote_addr;
        }
}
```

### 4. 적용 및 검증
```bash
# 기존 링크 삭제하기
sudo rm /etc/nginx/sites-enabled/default

# 만든 파일 링크 걸어주기
sudo ln -s /etc/nginx/sites-available/[이름] /etc/nginx/sites-enabled/

# 확인하기
ls -l /etc/nginx/sites-enabled/

# 설정한 파일에 문제 있는지 체크
sudo nginx -t
```


## ⚖️ 로드 밸런싱 알고리즘 비교
| 알고리즘                  | 설명                 | 추천 사용 사례                |
| --------------------- | ------------------ | ----------------------- |
| **Round Robin(Default)**       | 순차 분배              | 서버 스펙이 모두 동일할 때         |
| **Weighted RR**       | 가중치 기반 분배          | 서버 성능이 다를 때             |
| **Least Connections** | 연결 수가 가장 적은 서버에 분배 | API 요청 길이가 긴 서비스        |
| **IP Hash**           | 동일 사용자 요청 → 동일 서버  | 세션 공유 어려울 때 (Redis 미사용) |
| **Hash**              | 쿠키/헤더 기반 라우팅       | A/B 테스트, 특정 사용자 고정 라우팅  |


