1. Converter<I, O>
    - 파라미터 I 가  > O 타입으로 변환해서 나옴
    - convert 구현
2. ConversionService
   - DefaultConversionService 에 컨버터를 등록 
   - 컨버팅 가능 확인여부 체크, 컨버팅
   - convert(data, 반환될 type)
   - 등록하는 부분과, 사용하는 부분을 분리하고 의존관계 주입을 사용해야한다.
3. 스프링에 적용
   - WebMvcConfigurer 의 addFormatters 를 구현하여 addConverter를 등록해주면
   - 스프링 내부에서 ConversionService 를 제공해주는데 여기에 컨버터를 추가해준다.
4. 뷰템플릿에 적용
   - 타임리프는 렌더링 시에 컨버터를 적용해서 렌더링 하는 방법을 편리하게 지원한다.
   - 컨버터 적용 X : ${}, 컨버터 적용 O : ${{}}
   - form 은 자동으로 converting 시켜준다
     - th:field 속성을 사용하면 자동으로 컨버팅 한다 *{ipPort}
     - th:value 속성을 사용하면 컨버팅 하지 않는 값을 보여준다 ${ipPort} 

5. Formatter
   - 객체를 특정한 포맷에 맞추어 출력 또는 그 반대
   - Formatter 를 상속하여 parse, print를 구현한다
   - parse의 리턴값은 Long 이다.
   
6. Formatter 또한 converter와 동일하게 ConversionService에 등록하여 사용한다
   - FormattingConversionService 를 사용하면 되고, 구현 객체는 DefaultFormatingConversionService 를 사용
   - DefaultFormatingConversionService 의 상속관계를 따라가면 ConverterService, ConverterRegistry 모두 상속 하고
   - 있으므로 Formatter, Converter 모두 등록할 수 있다.
   - * 스프링 내부에서는 DefaultFormattingConversionService 를 상속 받은 WebConversionService를 사용한다.

7. Spring에 적용 해보자 WebConfiguration에 등록 registry.addFormatter(new MyNumberFormatter());

8. 스프링이 기본으로 제공하는 formatter
   - 애노테이션 기반으로 원하는 형식을 지정할 수 있는 포멧터 두가지를 기본제공
     - @NumberFormat : 숫자 관련 형식 지정 포멧터 사용, NumberFormatAnnotationFormatterFactory
     - @DateTimeFormat : 날짜 관련 형식 지정 포멧터 사용, Jsr310DateTimeFormatAnnotationFormatterFactory


* 주의
* HttpMessageConver에는 컨버전 서비스가 적용되지 않는다.
* 특히 객체를 JSON으로 변환할때 HttpMessageConver의 역할은 HTTP 메시지 바디의 내용을 객체로 변환하거나,
* 객체를 HTTP 메시지 바디에 입력하는것이다. 
* 이역할을 Jackson 같은 라이브러리를 사용하는데, 이 객체를 JSON으로 변환한다면 그 결과는 Jackson 라이브러리에 달린것이다.
* ConversionService는 @RequestParam, @ModelAttribute, @PathVariable, 뷰템플릿 등에서 사용되는 것이다.