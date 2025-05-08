package mate.academy.mapstruct.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.NullValueCheckStrategy;

@org.mapstruct.MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        implementationPackage = "<PACKAGE_NAME>.impl",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public class MapperConfig {
}
