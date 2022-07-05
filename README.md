# GET2KNOW

![GET2KNOWbg.png](README%20ed8a4bc7186244fbae5244c277fb03be/GET2KNOWbg.png)


> 처음 만난 친구의 이름을 외우기 어렵다구요? GET2KNOW가 도와줄게요!

*GET2KNOW는 여러가지 방식으로 얼굴 사진과 이름, 정보를 매치하여 새로 만난 친구의 이름을 더욱 빠르게 외울수 있도록 도와주는 앱입니다.*

## Authors

이혜림(카이스트 18): [https://github.com/hermioneee2](https://github.com/hermioneee2)

박준현(고려대 20): [https://github.com/channelsucre](https://github.com/channelsucre) 


## 기능 개요

<p float="left">
  <img src="/README%20ed8a4bc7186244fbae5244c277fb03be/Screenshot_20220705-193534_Get2Know.jpg" width="200" />
  <img src="/README%20ed8a4bc7186244fbae5244c277fb03be/Screenshot_20220705-193556_Get2Know.jpg" width="200" /> 
  <img src="/README%20ed8a4bc7186244fbae5244c277fb03be/Screenshot_20220705-193736_Get2Know.jpg" width="200" />
</p>

- **TAB1 CONTACTS**
친구들의 이름과 연락처를 보여줘요. 학교나 학번과 같은 기본 정보를 확인할 수 있고 전화나 문자를 걸 수도 있어요!
- **TAB2 GALLERY**
평범해보이는 사진첩이지만, 숨겨진 기능이 있다구요! 사람들의 사진을 클릭하면 카드가 뒤집히면서 이름과 정보를 확인할 수 있어요. 퀴즈로 넘어가기 전 카드들을 클릭해보며 이름 외우기를 연습해봐요.
- **TAB3 QUIZ**
이름과 사진을 외운 것 같다구요? 퀴즈로 얼마나 외웠는지, 얼마나 빠르게 답할 수 있는지 확인해봐요. 혹시 너무 어렵다면 카드를 스와이프해서 일단 스킵!

APK 다운로드 링크: https://drive.google.com/file/d/1sNrTm6fw4PU2MBzwzOM6w68O6G3piLWf/view?usp=sharing

## **권장 사용 환경 및 개발 환경**

- OS: Android (8.0 Oreo or Later)
- Language: JAVA 8
- IDE: Android Studio
- Target Device: Galaxy S7

## TAB1 CONTACTS

<p float="left">
  <img src="/README%20ed8a4bc7186244fbae5244c277fb03be/Screenshot_20220705-193534_Get2Know%201.jpg" width="300" />
  <img src="/README%20ed8a4bc7186244fbae5244c277fb03be/Screenshot_20220705-193543_Get2Know.jpg" width="300" />
</p>

### 주요 기능

- 첫번째 화면에서는 친구들의 사진과 이름을 볼 수 있습니다. 오른쪽의 전화나 문자 아이콘을 눌러 빠르게 연락할 수 있습니다.
- 사진이나 이름을 누르면 친구의 추가 정보와 함께 연락처를 볼 수 있습니다. 아래 버튼을 눌러 친구에게 연락할 수 있습니다.

### 기술 설명

- RecyclerView에 기반하여 JSON 파일에 저장되어 있는 정보를 보여줍니다.
- Dexter로 사용자의 전화 및 SMS에 접근할 수 있도록 권한을 부여하였습니다.
- 상세 정보가 나오는 화면은 별도의 Activity로 구현하였습니다. CardView, ImageView, TextView, RelativeLayout 등 다양한 요소를 활용하여 화면을 구성하였으며, Activity의 lifecycle을 활용하여 뒤로가기 기능을 하는 버튼 또한 추가하였습니다.

### 시행착오 및 아쉬운 점

- 처음에는 디바이스에서 연락처 정보를 직접 받아오는 방식으로 구현하였습니다. 그러나 이후 두번째와 세번째 탭을 구현하는 과정에서 정보를 하나로 통합하여 관리하는 방식의 필요성이 대두되어 json으로 저장하고, 이를 파싱하도록 변경하였습니다.

## TAB2 GALLERY

<p float="left">
  <img src="/README%20ed8a4bc7186244fbae5244c277fb03be/Screenshot_20220705-193550_Get2Know.jpg" width="200" />
  <img src="/README%20ed8a4bc7186244fbae5244c277fb03be/Screenshot_20220705-193556_Get2Know%201.jpg" width="200" />
  <img src="/README%20ed8a4bc7186244fbae5244c277fb03be/Screenshot_20220705-193612_Get2Know.jpg" width="200" />
</p>

### 주요 기능

- 갤러리에서 친구들의 사진을 모아볼 수 있습니다.
- 사진을 클릭하면 카드가 뒤집히면서 이름과 정보를 볼 수 있습니다.

### 기술 설명

- GridView와 EasyFlipView에 기반하여, 앞면에는 사진이, 뒷면에는 해당하는 사람의 정보가 표시되어 있는 카드들로 그리드 모양 갤러리를 제작했습니다.
- 각 카드들에 CardView와 유사한 형태의 둥근 모서리를 추가하기 위해 RoundedCornerLayout (child layout들에 둥근 모서리를 적용해 보여주는 layout)과 RoundedImageView (ImageView를 상속받은 클래스로, 이미지에 둥근 모서리를 적용하는 View)를 사용했습니다. 이 과정에서 의도한 결과가 쉽게 나오지 않아 아래와 같이 시행착오를 겪었으나, 결과적으로 해결책을 찾아 내었습니다.

### 시행착오

- 처음에는 RoundedCornerLayout을 ImageView에도 적용하려 하였으나, 일부 이미지가 정상적으로 잘리지 않거나, 이미지가 의도하지 않은 위치로 자리를 옮기는 등의 현상이 발생하였습니다.
- GridView와 RoundedCornerLayout, 그리고 EasyFlipView 간 렌더하는 방식에 있어 문제가 있는 것으로 생각되었기에, RoundedCornerLayout을 대체할 방법을 다방면으로 찾아보았습니다.
- 이 과정에서 ImageView의 이미지 각각에 mask를 씌우는 방식으로 구현할 수 있을지도 모르겠다는 아이디어가 떠올랐고, 조사를 통해 유사한 기능을 하는 RoundedImageView를 찾아 성공적으로 적용할 수 있었습니다.

## TAB3 QUIZ

<p float="left">
  <img src="/README%20ed8a4bc7186244fbae5244c277fb03be/Screenshot_20220705-193620_Get2Know.jpg" width="200" />
  <img src="/README%20ed8a4bc7186244fbae5244c277fb03be/Screenshot_20220705-193736_Get2Know%201.jpg" width="200" />
  <img src="/README%20ed8a4bc7186244fbae5244c277fb03be/Screenshot_20220705-193936_Get2Know.jpg" width="200" />
</p>

### 주요 기능

- 퀴즈를 설명하는 팝업으로 시작합니다. 시작버튼을 누르면 타이머가 작동되면서 퀴즈를 시작할 수 있습니다.
- 퀴즈는 인물 사진이 나오면 그 친구의 정보를 아래에서 스크롤해서 맞추는 방식입니다. 정답이 맞으면 자동으로 다음 카드로 넘어갑니다.
- 만일 너무 어렵다면, 카드를 직접 스와이프해서 스킵할 수 있습니다.
- 모든 인물사진을 맞추거나 스킵하여 퀴즈가 끝나면 걸린 시간과 스킵한 카드의 개수를 확인할 수 있습니다. 또 도전하고 싶다면 TRY AGAIN 버튼을 눌러서 다시 퀴즈를 시작합니다.

### 기술 설명

- 탭3 메인화면 상단의 스와이프 가능한 카드는 SwipeStack 라이브러리를 활용하여 자연스러운 스와이프 모션을 구현하였습니다.
- 탭3 메인화면 하단의 Scroll picker는 Recycler View에 기반한 WheelView-3d를 참고하여 구현하였습니다. 입체감이 느껴지도록 WheelView-3d내부에서 사용한 카메라와 매트릭스 전환을 차용하였습니다.
- 타이머 기록의 경우 TimeIt 라이브러리를 이용하여 시간을 기록하였습니다.
- 시작화면과 퀴즈 종료시 뜨는 모달의 경우 같은 Fragment에 다른 컴포넌트를 겹쳐 넣어서 visibility를 gone 또는 visible로 설정하여 조정하였습니다.
- 시작화면 버튼을 누르면 클릭 이벤트를 받아 타이머를 시작하고, 인물사진의 스택에 아무것도 남지 않았을 때 타이머를 멈추고 퀴즈 종료시 해당 기록을 보여줍니다.

### 시행착오 및 아쉬운 점

- 탭3 메인화면 하단의 Scroll picker의 경우 RecyclerView를 이용하여 직접 구현하려 했으나 가끔 선택된 텍스트를 감지하는 데 오류가 있어 이를 해결하고, 더 미려한 디자인을 도입하기 위해 이미 구현된 WheelView-3d로 다시 구현하게 되었습니다.
- 이에 결과적으로 유사한 결과물을 위해 두 번의 작업을 하게 되었으나, 단순히 구현된 것을 이용하지 않고 직접 구현의 방식을 생각하고 코드를 작성하는 과정에서 안드로이드 앱의 작동원리에 대해 많이 배울 수 있었습니다. 이 경험은 이후 앱의 다른 부분들을 구현할 때에도 도움이 되었으며, 무엇보다 실패를 두려워하지 않고 도전해볼 수 있는 자세를 가지게 해 주었습니다.
- 모달 구현의 경우 dialogView를 활용하여 구현하려고 했으나 dialogView fragment안에 구현되어있는 클릭 이벤트를 세번째 탭이 있는 fragement에서 넘겨받아 타이머 기록 시작을 하는 것을 구현하지 못해 한 fragment 내에서 visibility를 조정하는 식으로 변경하였습니다.

## GENERAL

### Json parsing과 데이터 공유

- 데이터 저장의 방식으로는 json을 선택하였습니다. 각각의 사람에 대해 연락처는 물론, 대응하는 프로필 이미지나 대학, 학번과 같은 추가적인 정보를 저장하여야 하기 때문입니다.

### 시행착오 및 아쉬운 점

- Json에서 parsing된 정보는 Commons라는 클래스를 사용하여 저장하였습니다. 해당 클래스를 import 해주면 다른 activity나 fragment, 심지어 adapter와 같은 특수한 클래스에서도 정보에 접근하기기가 용이하기 때문입니다. 다만 이런 방식은 Commons에 저장한 모든 정보를 메인 메모리에 상주시키기 때문에 프로그램의 반응성이나 성능 면에서 이상적이지 않습니다. 다음에 기회가 있다면 이러한 부분을 더 정교한 방식으로 처리할 방법에 대해 더 조사하여 보고 싶습니다.

## THANKS TO

### TAB1

- 연락처 보여주는 방식과 접근권한 연결방식
[https://www.geeksforgeeks.org/how-to-create-contacts-app-in-android-studio/](https://www.geeksforgeeks.org/how-to-create-contacts-app-in-android-studio/)

### TAB2

- GridView 참고자료
[https://developer.android.com/reference/android/widget/GridView](https://developer.android.com/reference/android/widget/GridView)
- EasyFlipView 라이브러리
[https://github.com/wajahatkarim3/EasyFlipView](https://github.com/wajahatkarim3/EasyFlipView)
- [Commons.java](http://Commons.java) 구현 참고자료
[https://github.com/editadiary/cs496_proj1_jysj/blob/main/app/src/main/java/com/example/myapplication/Common.java](https://github.com/editadiary/cs496_proj1_jysj/blob/main/app/src/main/java/com/example/myapplication/Common.java)

### TAB3

- SwipeStack 라이브러리
[https://github.com/flschweiger/SwipeStack](https://github.com/flschweiger/SwipeStack)
- SwipeStack 라이브러리 활용 예시
[http://www.devexchanges.info/2016/11/cards-stack-like-tinder-application-in.html](http://www.devexchanges.info/2016/11/cards-stack-like-tinder-application-in.html)
- WheelView-3d 구현방식
[https://github.com/youxiaochen/WheelView-3d](https://github.com/youxiaochen/WheelView-3d)
- TimeIt 라이브러리
[https://github.com/yashovardhan99/TimeIt](https://github.com/yashovardhan99/TimeIt)

### GENERAL

- 탭 전환 방식
[https://4whomtbts.tistory.com/49](https://4whomtbts.tistory.com/49)
- JSON 파싱 및 기본 디스플레이
    
    [https://lktprogrammer.tistory.com/175](https://lktprogrammer.tistory.com/175)
    

### COPYRIGHT

- 아이콘 이미지 
[https://flyclipart.com/download-png#friend-interface-male-icon-with-png-and-vector-format-for-free-796177.png](https://flyclipart.com/download-png#friend-interface-male-icon-with-png-and-vector-format-for-free-796177.png)
