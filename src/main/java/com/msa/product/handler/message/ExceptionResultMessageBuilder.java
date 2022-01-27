package com.msa.product.handler.message;

public class ExceptionResultMessageBuilder {
    private ExceptionMessageProvider provider;

    public ExceptionResultMessageBuilder(ExceptionMessageProvider provider) {
        this.provider = provider;
    }

    public String getResultMessage() {
        final StringBuilder resultMessageBuilder = new StringBuilder();
        while (provider.hasNext()) {
            provider.next();
            resultMessageBuilder
                    .append("['")
                    .append(getPopertyName(provider.getFieldName())) // 유효성 검사가 실패한 속성
                    .append("' is '")
                    .append(provider.getInput()) // 유효하지 않은 값
                    .append("'. ")
                    .append(provider.getErrorMessage()) // 유효성 검사 실패 시 메시지
                    .append("]");

            if (provider.hasNext()) {
                resultMessageBuilder.append(", ");
            }
        }
        return resultMessageBuilder.toString();
    }

    private String getPopertyName(String propertyPath) {
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1); // 전체 속성 경로에서 속성 이름만 가져온다.
    }
}
