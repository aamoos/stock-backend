# 만들게된 계기
미국주식 인기차트를 한눈에 보고 선택한 종목의 실시간 차트및 재무제표를 확인하는 사이트를 만들어보고 싶어서

# stock-backend

# 개발환경
- inteliJ
- Spring boot
- Gradle
- h2 (local)

# 사용기술
- spring data jpa

# openApi
- https://query1.finance.yahoo.com/v1/finance/screener/predefined/saved?scrIds=most_actives (인기차트)
- https://query1.finance.yahoo.com/v8/finance/chart (실시간차트)
- https://financialmodelingprep.com/api/v3 (재무제표)

# 화면
## 인기차트
![image](https://github.com/user-attachments/assets/85427864-f1f2-45d5-a65f-e6ae1e45a223)
- 10개, 20개, 50, 100개 보고싶은 리스트의 개수를 선택한만큼 인기차트 표현
- 검색창에 티커명 입력후 조회시 실시간 차트 및 재무제표 확인 가능한 상세화면 이동

## 상세화면
![image](https://github.com/user-attachments/assets/9d1fc77f-c2cc-4c4d-9cb9-a60a3dadbf9b)

![image](https://github.com/user-attachments/assets/9d25c6cb-afb2-45e0-851d-28a494e50d4a)
- SEC보고서 버튼 클릭시 해당 년도 SEC 보고서 링크 화면으로 이동




