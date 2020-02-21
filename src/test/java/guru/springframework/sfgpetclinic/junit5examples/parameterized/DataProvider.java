package guru.springframework.sfgpetclinic.junit5examples.parameterized;

import guru.springframework.sfgpetclinic.model.OwnerType;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class DataProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(OwnerType.values()).map(Arguments::of);
    }
}
