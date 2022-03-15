# spring_boot_001-2-2
태고의달인 wiki,음원다운로드사이트
<img src="https://taiko.namco-ch.net/taiko/images/top/img_mv.jpg">

# 기능 

<img width="1212" alt="스크린샷 2021-10-24 오후 11 46 04" src="https://user-images.githubusercontent.com/69393030/138599228-fc2806ac-cefb-4f5a-912c-c2e7a3b8163d.png">

곡 검색 기능 , 음원 다운로드 , tja 파일 다운로드 기능 , 미리듣기 기능 구현 
# 태고의달인 최신 수록곡 크롤링 기능 
<img width="1212" alt="스크린샷 2021-10-24 오후 11 46 43" src="https://user-images.githubusercontent.com/69393030/138599269-b958a7a1-c818-4aad-b154-de55c99175d1.png">

태고의달인 최신 수록곡을 공식 홈페이지에서 크롤링하여 데이터베이스에 저장하는 기능입니다.
# 채보 , 음원 업로드 기능 
<img width="1104" alt="스크린샷 2021-10-24 오후 11 47 46" src="https://user-images.githubusercontent.com/69393030/138599304-a2b7c69b-fadd-4dc2-8614-0b1c1c71b055.png">
유저가 자유롭게 비공식 , 공식 등의 태고의달인 음원 , tja 파일 등의 첨부파일을 업로드하고 다운로드 할수있게 구현했습니다.
# 태고의달인 위키 기능 

<img width="1116" alt="스크린샷 2021-10-24 오후 11 48 32" src="https://user-images.githubusercontent.com/69393030/138599337-a0a475b0-cb35-4214-8f14-acb3e56d7338.png">

태고의달인 곡에 대한 설명 , 위키 문서를 검색할수있는 기능입니다.
# 위키 검색 결과 
<img width="1116" alt="스크린샷 2021-10-24 오후 11 49 11" src="https://user-images.githubusercontent.com/69393030/138599368-1f5c8f3e-feb5-467c-ac62-cf291a5f07b3.png">

<img width="1116" alt="스크린샷 2021-10-24 오후 11 49 27" src="https://user-images.githubusercontent.com/69393030/138599380-4a02e46f-7756-45f4-9e43-c0337fa8409b.png">

데이터베이스에 저장된 곡의 위키 정보를 가져와서 보여주는 기능을 구현했습니다.
# 위키 문서 생성하기 
<img width="1116" alt="스크린샷 2021-10-24 오후 11 50 18" src="https://user-images.githubusercontent.com/69393030/138599410-7796c734-479c-44e2-ad8d-b11fa472acf6.png">
또한 유저들이 자유롭게 곡에대한 위키 문서를 생성할수 있는 기능을 구현했습니다. 

# 자유게시판 create api 생성
1) 유저들이 자유롭게 익명으로 이미지를 올리고 댓글형식의 게시판을 구현하는것을 중점으로한다.
<img width="1131" alt="스크린샷 2022-03-14 오후 10 27 40" src="https://user-images.githubusercontent.com/69393030/158181440-d88d035d-bb38-4ef2-8159-8a940c8bef05.png">
2) list 불러오는 api 생성 (2022-3-14)
<img width="1131" alt="스크린샷 2022-03-14 오후 10 28 30" src="https://user-images.githubusercontent.com/69393030/158181581-f154ce6b-65fd-4885-90d5-4bac3f0843f5.png">

# 댓글작성
익명으로 댓글을 작성하여 커뮤니케이션을 할수있다.
사진 등을 첨부할수있다.
<img width="1131" alt="스크린샷 2022-03-15 오후 7 51 08" src="https://user-images.githubusercontent.com/69393030/158362085-93558c56-c4fd-4c1b-b910-ce77bee26f84.png">
