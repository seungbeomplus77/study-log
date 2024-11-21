## 💡 Lessons Learned
## 📅 작성일: 2024-11-21

# **파일처리**

1. 파일 처리 시 필수 사항
- `enctype: 파일을 서버로 전송할 때 반드시 enctype="multipart/form-data"`를 사용해야 합니다. 이 속성을 사용하지 않으면, 파일은 URL 쿼리 스트링으로 보내지기 때문에 제대로 처리되지 않습니다.
- `boundary: multipart/form-data` 방식에서는 파일 항목을 구분하는 구분자(boundary)가 필요합니다. 서버는 이 `boundary`를 통해 각 파일을 분리하여 처리합니다.
---
2. HTTP 메서드
- GET: 데이터를 URL 쿼리 스트링에 포함하여 전송합니다. 일반적으로 `getParameter()`로 값을 받으며, 파일은 처리할 수 없습니다.
- POST: 데이터를 HTTP 요청 바디에 포함하여 전송합니다. `getParameter()`로도 값을 받을 수 있지만, 파일은 `inputStream`을 사용하여 처리해야 합니다.
---
3. 서블릿을 사용한 파일 처리
- 서블릿 3.0 이상에서는 `@MultipartConfig` 애노테이션을 사용하여 파일 업로드를 처리할 수 있습니다.
- 파일은 `req.getParts()`로 받아오며, 각각의 파일 항목은 Part 객체로 처리됩니다.
- `getParts()` 메서드는 요청에 포함된 모든 파트들을 반환합니다. 여러 파일이 전송된 경우, 각 파일은 별도의 Part 객체로 관리됩니다.
- 일반적인 폼 파라미터는 `req.getParameter("parameterName")`로 받을 수 있지만, 파일은 `req.getParts`()로 받아 처리해야 합니다.
---
4. 파일 및 폴더 관리
- `file.exists():` 지정한 파일이 존재하는지 확인합니다. 파일이 없으면 false, 있으면 true를 반환합니다.
- `file.mkdirs():` 폴더를 생성합니다. 경로에 포함된 모든 폴더가 존재하지 않으면, 해당 폴더를 생성합니다.
---
5. 파일 저장 위치
- 클라이언트가 파일을 웹 브라우저에서 접근하려면, WEBAPP 디렉토리 내에 파일을 저장해야 합니다.
- META-INF, WEB-INF 디렉토리 내에는 파일을 저장할 수 없으며, 이 경로는 웹에서 접근할 수 없습니다.
- 또한, 서블릿 이름과 파일 이름이 동일하면 충돌이 발생할 수 있으므로 피해야 합니다.
---
6. 파일 업로드 관련 HTML
- multiple 속성: `<input type="file" multiple />`를 사용하면 파일을 여러 개 선택하여 업로드할 수 있습니다.
- Content-Type: 파일 전송 시에는 Content-Type을 application/octet-stream으로 설정하여 서버가 파일로 인식할 수 있게 합니다.(서블릿 상으로도 설정 할수있음)

