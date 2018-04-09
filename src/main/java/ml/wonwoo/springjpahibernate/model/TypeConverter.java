package ml.wonwoo.springjpahibernate.model;

import javax.inject.Inject;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TypeConverter implements AttributeConverter<Type, String> {

    //FIXME CDI Injection Not working
    @Inject
    private PersonRepository personRepository;

    @Override
    public String convertToDatabaseColumn(Type attribute) {
        return attribute.getType();
    }

    @Override
    public Type convertToEntityAttribute(String dbData) {
        return new Type(dbData);
    }
}
