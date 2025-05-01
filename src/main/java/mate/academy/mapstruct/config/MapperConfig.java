package mate.academy.mapstruct.config;

import mate.academy.mapstruct.mapper.GroupMapper;
import mate.academy.mapstruct.mapper.SubjectMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.NullValueCheckStrategy;

@org.mapstruct.MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl",
        uses = {GroupMapper.class, SubjectMapper.class}
)

public interface MapperConfig {
}
