## 스프링과 타입 변환
 - 컨버터 인터페이스 S를 받고 T로 반환  
 - public interface Converter<S, T> T converter(S source)
 - 타입 컨버터를 등록하고 관리하면서 편리하게 변환 기능을 제공하는 역할을 하는 무언가가 필요하다.

## 컨버전 서비스
 - 컨버터를 모아두고 그것들을ㅇ 묶어서 편리하게 사용할 수 있는 기능을 제공, 이를 컨버전 서비스라고함(ConversionService)
 - DefaultConversionService conversionService = new DefaultConversionService();
 - conversionService.addConverter(Object obj);
 - conversionService.convert(input value, output Obj)

 - DefaultConversionService는 두 인터페이스를 구현 했다
   - ConversionService(컨버서 사용에 초점), ConversionRegistry(컨버터 등록에 초점)


## 스프링에 컨버터 적용