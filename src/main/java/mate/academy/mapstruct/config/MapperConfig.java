package mate.academy.mapstruct.config;

@org.mapstruct.MapperConfig(
        componentModel = "spring",
        injectionStrategy = org.mapstruct.InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl"
)
public class MapperConfig {
}
