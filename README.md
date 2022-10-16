# GoogleMapApp
자바와 구글 Maps API를 이용해서 랜덤으로 좌표를 찍어주는 보물찾기 앱입니다.
![image](https://user-images.githubusercontent.com/88234731/192283562-53b5c18f-313c-4686-89f6-7318a9220811.png)<br><br>

앱을 처음 실행하면 저희 학교 위치로 마커가 찍힙니다.<br>
![제목 없음](https://user-images.githubusercontent.com/88234731/196030292-1fe183c3-b14e-4bd0-bebe-92d2ead16807.png)
```java
private GoogleMap mMap; // 맵 모듈
private LatLng SUNGIL = new LatLng(37.433774, 127.145662); // 학교 좌표

public void onMapReady(final GoogleMap googleMap) {

  mMap = googleMap;

  MarkerOptions markerOptions = new MarkerOptions(); // 객체 생성
  markerOptions.position(SUNGIL); // 위치
  markerOptions.title("성일정보고등학교"); // 제목
  markerOptions.snippet("성남시의 고등학교"); // 설명
  mMap.addMarker(markerOptions); // 마커 추가

  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SUNGIL, 10)); // 카메라 이동
}
```
<br>

보물찾기 버튼을 누르면 랜덤으로 좌표가 찍히고 마커가 찍힙니다.<br>
![제목 없음](https://user-images.githubusercontent.com/88234731/196030719-412cd14d-47fd-42a3-ab14-f748bc8be0c0.png)
```java
private double x, y; // 좌표
private int count = 0; // 카운트

public LatLng getRandomLocation(LatLng c, int radius) { // 랜덤 위치 생성
  double d2r = Math.PI / 180;
  double r2d = 180 / Math.PI;
  double earth_rad = 6378000f; //지구 반지름 근사값

  double r = new Random().nextInt(radius) + new Random().nextDouble();
  double rlat = (r / earth_rad) * r2d;
  double rlng = rlat / Math.cos(c.latitude * d2r);

  double theta = Math.PI * (new Random().nextInt(2) + new Random().nextDouble());
  y = c.longitude + (rlng * Math.cos(theta));
  x = c.latitude + (rlat * Math.sin(theta));
  return new LatLng(x, y);
}

public void onClick(View view) { // 버튼 클릭
  getRandomLocation(SUNGIL, 1000); // 랜덤 범위

  count++; // 카운트 증가

  LatLng RANDOM = new LatLng(x, y); // 위치 지정

  Toast.makeText(getApplicationContext(), "보물을 찾았습니다.", Toast.LENGTH_SHORT).show(); // 토스트 메세지

  MarkerOptions markerOptions = new MarkerOptions();
  markerOptions.position(RANDOM);
  markerOptions.title(count+"번째 보물");
  markerOptions.snippet(count+"번째 보물입니다.");
  mMap.addMarker(markerOptions);

  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RANDOM, 17));
}
```
