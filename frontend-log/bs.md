`height: 100vh;`
- 100vh는 뷰포트 높이의 100%, 즉 화면 전체 높이에 해당합니다.(1은 1%)
- 사용하면 해당 요소의 높이가 화면의 전체 높이와 같아집니다. 이 방법은 페이지 레이아웃을 설정할 때 유용하게 사용될 수 있습니다. 예를 들어, 페이지가 스크롤되지 않고 화면에 꽉 차도록 만드는 데 사용됩니다.

`box-sizing: border-box;`
- 일일히 padding, width, margin 등 조정 안하고 쉽게 조정 할수있게 도와줌

--------------------------------------------------------------------------------------------------------

`display: flex;`
- 항목들이 한 줄에 나란히 배치
- 항목들이 크기와 공간을 유연하게 조정
- 세로 또는 가로 방향으로 정렬할 수 있음
- 항목들이 공간을 나누거나 정렬하는데 더 쉽게 조정

- Flexbox의 주요 속성들

### flex-direction: column;
- Flexbox 컨테이너 내의 항목들이 세로 방향으로 배치(기본은 row(가로))

### justify-content: 항목들이 주축(main axis)을 따라 어떻게 정렬될지 설정
- flex-start: 왼쪽(기본값)
- center: 가운데
- space-between: 양쪽 끝에 배치하고, 항목들 사이에 같은 간격
- space-around: 항목들 사이에 여백을 두고 배치

### align-items: 항목들이 교차축(cross axis)을 따라 어떻게 정렬될지 설정
- flex-start: 위쪽(기본값)
- center: 가운데
- stretch: 항목들이 높이에 맞게 늘어남

--------------------------------------------------------------------------------------------------------

# Bootstrap의 CSS 초기화(reset) 주요 코드들

1. Box-sizing 초기화
```
*::before,
*::after {
  box-sizing: border-box;
}
```

2. 기본 margin 제거
```
body {
  margin: 0;
}
```

3. 기본 body 설정
```
body {
  font-family: var(--bs-body-font-family);
  font-size: var(--bs-body-font-size);
  font-weight: var(--bs-body-font-weight);
  line-height: var(--bs-body-line-height);
  color: var(--bs-body-color);
  background-color: var(--bs-body-bg);
  -webkit-text-size-adjust: 100%;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
```

4. 헤딩 태그(h1-h6) 초기화
```
h1, h2, h3, h4, h5, h6 {
  margin-top: 0;
  margin-bottom: 0.5rem;
  font-weight: 500;
  line-height: 1.2;
}
```

5. 단락(p) 초기화
```
p {
  margin-top: 0;
  margin-bottom: 1rem;
}
```

6. 링크(a) 스타일 초기화
```
a {
  color: var(--bs-link-color);
  text-decoration: underline;
}

a:hover {
  color: var(--bs-link-hover-color);
}
```

7. 목록(ul, ol) 초기화
```
ol, ul {
  padding-left: 2rem;
  margin-top: 0;
  margin-bottom: 1rem;
}
```

8. 이미지 초기화
```
cssCopyimg {
  vertical-align: middle;
}
```

9. 테이블 초기화
```
table {
  caption-side: bottom;
  border-collapse: collapse;
}
```

10. 폼 요소 초기화
```
button {
  border-radius: 0;
}

input,
button,
select,
optgroup,
textarea {
  margin: 0;
  font-family: inherit;
  font-size: inherit;
  line-height: inherit;
}
```
---------------------------------------------------------------------------------
