package ro.tuc.ds2020.services.abstractions;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

@CrossOrigin
@JsonRpcService("/rpc")
public interface RPCService {
    List<List<Float>> getEnergyConsumptionOverLastNDays(@JsonRpcParam(value = "d") int d,
                                                        @JsonRpcParam(value = "userId") UUID userId);
    List<Float> getBaselineOfClient(@JsonRpcParam(value = "userId") UUID userId);

    TreeMap<String, Float> getProgram(@JsonRpcParam(value = "userId") UUID userId, @JsonRpcParam(value = "d") int d);
}
