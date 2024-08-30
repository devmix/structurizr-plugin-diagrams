import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Sergey Grachev
 */
public class Main {

    private static final void showHashAlgorithms(Provider prov, Class<?> typeClass) {
        String type = typeClass.getSimpleName();

        List<Provider.Service> algos = new ArrayList<>();

        Set<Provider.Service> services = prov.getServices();
        for (Provider.Service service : services) {
            if (service.getType().equalsIgnoreCase(type)) {
                algos.add(service);
            }
        }

        if (!algos.isEmpty()) {
            System.out.printf(" --- Provider %s, version %.2f --- %n", prov.getName(), prov.getVersion());
            for (Provider.Service service : algos) {
                String algo = service.getAlgorithm();
                System.out.printf("Algorithm name: \"%s\"%n", algo);


            }
        }

        // --- find aliases (inefficiently)
        Set<Object> keys = prov.keySet();
        for (Object key : keys) {
            final String prefix = "Alg.Alias." + type + ".";
            if (key.toString().startsWith(prefix)) {
                String value = prov.get(key.toString()).toString();
                System.out.printf("Alias: \"%s\" -> \"%s\"%n",
                        key.toString().substring(prefix.length()),
                        value);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            showHashAlgorithms(provider, MessageDigest.class);
        }

        new URL("http://localhost:8081/svg/LSwn2i903CRnFK-H-Q0-G2YTYb1SN3gNs0lre7T86eiYlhkdebpdyFt-aMJ1xZZEGPBX0rpN4gPhF8bYNHyFJGDL1MuySvh44rg8iXZ72xZ4KGXNrdnKSEozoh8CmHV0_Wqy0QJC3COwoR_MzrtxQpcvcMZYkM0-3snZMVnIH7cxsxf41G8l")
                .openConnection().getInputStream().readAllBytes();
    }
}
