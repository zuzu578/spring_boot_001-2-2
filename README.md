# 태고의달인 사이트 
<img src="https://taiko.namco-ch.net/taiko/images/top/img_mv.jpg">
# 기술스택
jsp , spring boot , oracle , es6 , jquery , vue js (template)


1) 태고의달인 음원 , 채보 다운로드 , 유저 업로드 사이트
 
2) 태고의달인 정보 (wiki 백과 ) 편집 , 작성 기능 

3) 태고의달인 커뮤니티 기능 

4) 태고의달인 수록곡 엑셀 다운로드 기능 (library : poi 사용)

5) 태고의달인 수록곡 크롤링 기능 (library : jsoup 사용)

# 개요 
태고의달인 이라는 게임의 수록곡 정보 , 음원 파일 등을 유저에게 제공하기 위한 사이트를 만들고 싶었으나 해당 정보에대한 rest api 가 없어서 직접 공식 사이트에서 수록곡을 장르별로 크롤링하여 데이터베이스에 저장하고 , 음원을 유저들이 자유롭게 올릴수있고 , 미리들을수 있으며 , 다운로드 할수 있도록 만들었습니다. 

또한 태고의달인 이라는 게임을 하는 유저들끼리 자유롭게 영상과 , 댓글을 게시하고 정보등을 올릴수있는 (wiki 백과) 기능을 추가하여 유저들이 자유롭게 정보를 제공하고 생성할수있는 웹사이트를 만들고 싶어서 해당 토이 프로젝트를 진행하게 되었습니다.
# 초기 버전
초기버전은 미리듣기 기능과 크롤링한 곡을 단순히 데이터를 보여주기 위한 정도의 목적으로 만들어 졌으나 , 커뮤니티에 해당 프로그램을 만들었다고 전시했더니 반응이 괜찮아서 기능을 추가하게 되었습니다.

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
<img width="1127" alt="스크린샷 2022-03-16 오후 3 26 32" src="https://user-images.githubusercontent.com/69393030/158529560-a58aa201-0018-4bb1-8444-b200ee94b9f9.png">

익명으로 댓글을 작성하여 커뮤니케이션을 할수있다.
사진 등을 첨부할수있다.
익명의 유저는 비밀번호를 입력하여 컨텐츠를 생성할수있다.
이때 입력된 비밀번호는 암호화하여 데이터베이스에 저장된다.
암호화 비밀번호 방식은 SHA-512 알고리즘 방식을 사용한다.
<img width="707" alt="스크린샷 2022-03-16 오후 3 25 55" src="https://user-images.githubusercontent.com/69393030/158529459-f8b43023-ba7b-4f0f-919f-96e79ec74f30.png">


# 자유게시판 댓글 삭제기능 
작성한 익명유저의 비밀번호를 조회하여 데이터를 삭제한다.
<img width="1131" alt="스크린샷 2022-03-16 오후 3 24 54" src="https://user-images.githubusercontent.com/69393030/158529304-df75bffa-6823-48bc-b98e-81b86bcd4d52.png">
# 동영상 첨부 , 재생기능 
사진뿐만아니라 영상컨텐츠도 올려서 볼수있도록한다.
<img width="1127" alt="스크린샷 2022-03-16 오후 7 14 30" src="https://user-images.githubusercontent.com/69393030/158567534-bacddb0b-ca72-4b95-b2e2-8152a3719550.png">


# 게시물에 댓글작성기능 
<img width="1099" alt="스크린샷 2022-03-20 오후 6 58 46" src="https://user-images.githubusercontent.com/69393030/159157169-fa7ca18b-788f-46d8-84c9-a745b8f50d50.png">


해당 영상이나 사진에 댓글을 달수있도록 한다.

# wiki 에 댓글기능 추가
<img width="1099" alt="스크린샷 2022-03-21 오후 5 54 10" src="https://user-images.githubusercontent.com/69393030/159229812-209ab69c-0a87-4d3b-8331-0b9be7a3bd9f.png">
# 댓글 페이지네이션 구현 
<img width="933" alt="스크린샷 2022-03-22 오후 9 47 47" src="https://user-images.githubusercontent.com/69393030/159485383-0aa8625b-454e-4e35-99a2-787c20088953.png">
이전 , 다음 버튼을 누르면 다음 댓글 , 이전댓글을 확인할수있다.

위키작성한 게시물에 대한 댓글 기능을 추가하였다.

# wiki 글 편집기능 
위키정보를 사용자가 수정할수 있도록 기능을 추가한다.
해당곡의 난이도 , 작가 , 작곡가 , 영상 정보 등 을 수정할수있다.

<img width="942" alt="스크린샷 2022-03-26 오후 8 33 26" src="https://user-images.githubusercontent.com/69393030/160237528-53437ec2-40f4-4b33-90e8-92aafcfa6568.png">

# ckeditor 를 이용한 여러개의 영상 첨부 
<img width="1001" alt="스크린샷 2022-03-23 오후 5 24 08" src="https://user-images.githubusercontent.com/69393030/159655139-129e2ecd-e8e8-4bb6-928a-171fecd37caa.png">
ckeditor 에서 embeded link 를 이용하여 여러개의 유튜브 링크를 첨부하여 영상을 여러개 올릴수 있도록한다.

# wiki 최근 게시물 보이기 
<img width="1188" alt="스크린샷 2022-03-23 오후 8 15 51" src="https://user-images.githubusercontent.com/69393030/159687399-de7224b4-1f5b-417d-82f6-e25facc8ed7e.png">

# 태고의달인 수록곡 exceldownload 기능 
<img width="700" alt="스크린샷 2022-03-25 오후 5 20 07" src="https://user-images.githubusercontent.com/69393030/160082047-d1bdabfa-99b7-4ca3-8907-7dfd6ab6b0d5.png">

태고의달인 수록곡을 excel 문서화 하여 사용자한테 제공한다.
<img width="1077" alt="스크린샷 2022-03-25 오후 5 20 29" src="https://user-images.githubusercontent.com/69393030/160082091-0931e123-65e0-4e05-9dc7-ae821cbb32b2.png">
# wiki , 댓글 , 게시글 생성시 사용자 Ip 주소를 database 에 저장 
<img width="840" alt="스크린샷 2022-03-26 오후 6 50 08" src="https://user-images.githubusercontent.com/69393030/160234190-82196477-dfe7-4c76-8c16-17dffb8cf47a.png">
게시글을 작성하면 사용자의 ip 주소를 데이터베이스에 저장한다.
<img width="942" alt="스크린샷 2022-03-26 오후 6 50 41" src="https://user-images.githubusercontent.com/69393030/160234220-a0da4ab8-156a-471d-a05c-d0995bef31b9.png">
글을 편집 ,생성할때 ip 주소를 표기한다.


# 문제 해결 과정 

1) 처음에 자유게시판 부분에서 클릭한 게시물에 대한 댓글을 남길때 모든 댓글창이 열려버리는 바람에 댓글작성이 되지않았고 , 이러한 문제를 해결하기위해 button 에 this 를 event 로 감지하여 e.prev()를 이용하여 해당 게시물에대한 댓글창만 열리도록 수정 하였습니다.
그런다음 열린 댓글 창의 입력된 부모 , 자식 엘레멘트를 제이쿼리의 child() , parent() 를 이용하여 값을 바꿨더니 게시글에대한 댓글이 정상적으로 작성되도록 유지보수 하였습니다.

2) 댓글 테이블을 설계할때 공통으로 댓글을 사용할수있도록 댓글에대한 테이블의 타입 컬럼을 추가하였습니다. wiki 게시판에서 사용될 댓글이면 type 은 wiki , 자유 게시판에서 사용될 댓글이면 freeTalkBoard 라는 타입을 설정하였고 , 테이블을 따로 설계할 필요없이 하나의 테이블에서 타입에 따라 데이터를 적재할수 있도록 만들었습니다.

3) 자유게시판에서 댓글을 가져올때 모든 데이터를 list 로 가져와야하기때문에  join 해서 데이터를 가져오기 힘든 상황이었습니다. 이때 저는 , 게시물 list 따로 , 댓글 따로 하여 , 게시물 idx 와 댓글 상위 node idx 가 같은 것끼리 find() 하여 묶어서 데이터를 보여줬습니다. 즉 , 스크립트로 이를 해결하였습니다.
