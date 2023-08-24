# highJava
고급 자바 (23/08/22 ~ ing)

## 1일차
### 컬렉션 프레임워크
### Vector
### ArrayList


## 2일차
### Stack
### Queue
### sort
- 정렬은 Collections.sort()메서드를 이용하여 정렬한다.
- Collections.sort()메서드는 기본적으로 내부 정렬 기준으로 정렬한다.
- 정렬(sort)와 관련된 interface는 Comparable, Comparator 이렇게 두 가지가 있다.
  - Comparable은 Collection에 추가되는 데이터 자체에 정렬 기준을 넣고 싶을 때(내부 정렬 기준) 구현하는 인터페이스이다.
  - Comparator는 외부에 별도로 정렬 기준을 구현하고 싶을 때(외부 정렬 기준) 구현하는 인터페이스이다.
  - Comparable에서는 compareTo()메서드를 재정의하고 Comparator에서는 compare()메서드를 재정의해야 한다.
  - String클래스, Wrapper클래스, Date클래스, File클래스 등에는 이미 내부 정렬 기준이 구현되어 있다.
  (내부 정렬 기준은 오름차순으로 처리되도록 구현되어 있다.)

## 3일차
내부 정렬 기준 구현  
외부 정렬 기준 구현
### List와 Set
- 차이점
  - List
    - 데이터의 순서(index)가 있다.
    - 중복되는 데이터를 저장할 수 있다.
  - Set
    - 데이터의 순서(index)가 없다.
    - 중복되는 데이터를 저장할 수 없다.
