# portfolio - 최종 팀 프로젝트(Elesco)


------------------------------------------------------------------------------------------------------------------------------------------

# 개발환경
>front-end
- javascript
- bootstrap 
- jquery 

>back-end
- Java
- Spring
- myBatis
- maven
- springBoot
- JPA
- lombok 
- oracle DB
- tomcat 





------------------------------------------------------------------------------------------------------------------------------------------

# DB 모델링
<div>
  <img src="https://user-images.githubusercontent.com/105841315/191449633-95f059d3-e0d5-47eb-a300-dcb199f2d037.png">
</div>




------------------------------------------------------------------------------------------------------------------------------------------

# 기능별 주요 로직
>  - Back-End SpringMVC 카카오 Daum 주소 API 연동 처리
- 주소 API 연동
### register.jsp : 주소api
~~~javascript
function execution_daum_address(){
	new daum.Postcode({
		 oncomplete: function(data) {
             var addr = ''; 
             var extraAddr = ''; 

             if (data.userSelectedType === 'R') { 
                 addr = data.roadAddress;
             } else { 
                 addr = data.jibunAddress;
             }
             if(data.userSelectedType === 'R'){
                 if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                     extraAddr += data.bname;
                 }
                 if(data.buildingName !== '' && data.apartment === 'Y'){
                     extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                 }
                 if(extraAddr !== ''){
                     extraAddr = ' (' + extraAddr + ')';
                 }
                 addr += extraAddr
             } else {
                 addr += ' '
             }

             $(".USER_POST").val(data.zonecode);
             $(".USER_ADDR1").val(addr)
             $(".USER_ADDR2").attr("readonly",false);
             $(".USER_ADDR2").focus();
         }
     }).open();
~~~
### 주소 API
<div>
  <img src="https://user-images.githubusercontent.com/105841315/191143007-c847b3ed-c9de-40b9-88dd-9d8ca3958166.gif">
</div>


>  - SpringMVC 회원가입시 중복 아이디 닉네임 체크

- 중복체크
### register.jsp : 중복체크

~~~Java
   <div class="int-area">
                <input type="text"  name="USER_ID" id="id"  autocomplete="off"  required>
                <label for="id">USER NAME</label>
                <span class="id_not_exist">사용 가능한 아이디입니다</span>
				        <span class="id_exist">아이디가 이미 존재합니다.</span>
            </div>
~~~

~~~JavaScript
$("#id").on("propertychange change keyup paste input",function(){
	
	var userId = $('#id').val();
	var data = {userId:userId} 
	$.ajax({
		type :"post",
		url : "/users/userIdChk",
		beforeSend : function(xhr)
        {   
            xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
        },
		data : data,
		success : function(result){
			
			if(result != 'fail'){
				$(".id_not_exist").css("display","inline-block");
				$(".id_exist").css("display","none");
			}else{
				$(".id_exist").css("display","inline-block");
				$(".id_not_exist").css("display","none");
			}
		}
 	});
~~~

### UsersController.java : 중복체크

~~~java
public String userIdChk(String userId) {
		int result = userserivce.idCheck(userId);
		if(result != 0) {
			return "fail";
		} else {
			return "success";
		}
	}
  ~~~


### UsersMapper.xml : 중복체크

~~~xml
<select id="idCheck" resultType="int">
select count(*) from users where USER_ID = #{USER_ID}
</select>
~~~
  
  
<div>
  <img src="https://user-images.githubusercontent.com/105841315/191143007-c847b3ed-c9de-40b9-88dd-9d8ca3958166.gif">
</div>


>  - SpringMVC 시큐리티 Bcrypt 적용

- 비밀번호 암호화
### UsersController.java : 비밀번호 암호화

~~~java
@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	
	
	@PostMapping("/insert")
	public String insertpost(UsersVO uservo) {
		String originPw = "";
		String bcryptPw = "";
		
		originPw = uservo.getUSER_PW();
		bcryptPw = pwEncoder.encode(originPw);
		uservo.setUSER_PW(bcryptPw);
		
		
		log.info("유저정보"+uservo);
		userserivce.userInsert(uservo);
		return "redirect:/";
	}
	
~~~

<div>
  <img src="https://user-images.githubusercontent.com/105841315/191143007-c847b3ed-c9de-40b9-88dd-9d8ca3958166.gif">
</div>


>  - SpringBoot 시큐리티 권한설정

- 권한설정

### SecurityConfig.java : 페이지 권한설정

~~~java
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated()
			.antMatchers("/admin/**","/board/register").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/loginForm")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/");
			
	}
~~~

<div>
  <img src="https://user-images.githubusercontent.com/105841315/191143007-c847b3ed-c9de-40b9-88dd-9d8ca3958166.gif">
</div>


>  - SpringBoot 회원관리 페이징, 검색처리 , 

- 페이징
### SecurityConfig.java : 회원관리 페이징 컨트롤러

~~~java
@GetMapping("/list")
	public void userList(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {
		Page<Users> ulist = usersRepository.findAll(pageable);
		if(field.equals("USERID")) {
			ulist = usersRepository.findByUSERIDContaining(word, pageable);
		}
		else if (field.equals("USERNICKNAME")) {
			ulist = usersRepository.findByUSERNICKNAMEContaining(word, pageable);
		}
		int pageNumber = ulist.getPageable().getPageNumber();
		int totalPages = ulist.getTotalPages();
		int pageBlock = 5;
		int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1;
		int endBlockPage = startBlockPage+pageBlock-1;
		endBlockPage = totalPages<endBlockPage? totalPages:endBlockPage;
		
		model.addAttribute("startBlockPage",startBlockPage);
		model.addAttribute("endBlockPage",endBlockPage);
		model.addAttribute("ulist",ulist);
		
		System.out.println(pageNumber);
		System.out.println(totalPages);
		
	}
~~~

### list.jsp : 회원관리 페이징 구현화면

~~~jsp
<!-- 이전 -->
<c:choose>
	<c:when test="${ulist.first}"></c:when>
	<c:otherwise>
		<li class="page-item"><a class="page-link" href="/users/list/?field=${param.field}&word=${param.word}&page=0">처음</a></li>
		<li class="page-item"><a class="page-link" href="/users/list/?field=${param.field}&word=${param.word}&page=${ulist.number-1}">&larr;</a></li>
	</c:otherwise>
</c:choose>

	<!-- 페이지 그룹 -->
<c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
	<c:choose>		
		<c:when test="${ulist.pageable.pageNumber+1 == i}">
			<li class="page-item disabled"><a class="page-link" href="/users/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="/users/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a></li>
		</c:otherwise>
	</c:choose>
</c:forEach>
			
		<!-- 다음 -->
<c:choose>
	<c:when test="${ulist.last}"></c:when>
	<c:otherwise>
		<li class="page-item "><a class="page-link" href="/users/list/?field=${param.field}&word=${param.word}&page=${ulist.number+1}">&rarr;</a></li>
		<li class="page-item "><a class="page-link" href="/users/list/?field=${param.field}&word=${param.word}&page=${ulist.totalPages-1}">마지막</a></li>
	</c:otherwise>
</c:choose>

~~~

<div>
  <img src="https://user-images.githubusercontent.com/105841315/191143007-c847b3ed-c9de-40b9-88dd-9d8ca3958166.gif">
</div>

### list.jsp :  검색처리

~~~jsp
<form action="/users/list" class="form-inline d-flex justify-content-end"
		method="GET">
		<select name="field" id="field" class="form-control form-control-sm">
			<option value="USERID">아이디</option>
			<option value="USERNICKNAME">닉네임</option>
		</select> 
		<input type="text" id="word" name="word" class="form-control form-control-sm"
			style="margin: 10px;"> 
		<input type="submit" class="btn btn-outline-info btn-sm" value="검색">
</form>
~~~

<div>
  <img src="https://user-images.githubusercontent.com/105841315/191143007-c847b3ed-c9de-40b9-88dd-9d8ca3958166.gif">
</div>

### list.jsp : 삭제할건지 묻고 삭제처리
~~~jsp
<c:forEach items="${ulist.content}" var="user">
	<tr>
		<td>${user.id}</td>
		<td>${user.USERID}</td>
		<td>${user.USER_EMAIL}</td>
		<td>${user.USER_REGDATE}</td>
		<td> <a href='/users/detail?id=<c:out value="${user.id}"/>'>이동 </a>  </td>
		<td> <a  onclick="return confirm('정말로 탈퇴시키겠습니까?');" href="/users/list/${user.id}">탈퇴</a>   </td>
	</tr>
</c:forEach>
~~~

<div>
  <img src="https://user-images.githubusercontent.com/105841315/191143007-c847b3ed-c9de-40b9-88dd-9d8ca3958166.gif">
</div>


------------------------------------------------------------------------------------------------------------------------------------------



# 프로젝트를 진행하며 느낀점
- 의존성 주입을 활용한 어노테이션으로 객체 자동 생성이 편하다.
- junit의 단위테스트의 통한 편리함 뷰페이지를 만들지 않아도 코드에 에러가 있는지 확인할 수 있다.
- 권한처리를  내가 하나하나 코딩 하는것이 아니고 spring security를 통해 쉽게 처리할 수 있다.
- lombok을 통해 setter, getter, tostring 작성을 생략 가능해 편하다.



