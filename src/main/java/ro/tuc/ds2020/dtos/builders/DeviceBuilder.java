package ro.tuc.ds2020.dtos.builders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dtos.DeviceDetailsDTO;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.repositories.UserRepository;

import java.util.Optional;

@Component
public class DeviceBuilder {

    @Autowired
    private UserRepository userRepository;

    private DeviceBuilder() {

    }

    public DeviceDetailsDTO toDeviceDetailsDTO(Device device) {
        if(device.getUser()!=null)
        {
            return new DeviceDetailsDTO(device.getId(), device.getDescription(),
                    device.getAddress(), device.getMax_consumption(), device.getAverage_consumption(),
                    device.getUser().getId(), device.getUser().getUsername()
            );
        }
        return new DeviceDetailsDTO(device.getId(), device.getDescription(), device.getAddress(), device.getMax_consumption(), device.getAverage_consumption());
    }
    public Device toEntity(DeviceDetailsDTO deviceDetailsDTO) {
        if(deviceDetailsDTO.getUserId()!=null)
        {
            Optional<User> optionalUser = userRepository.findById(deviceDetailsDTO.getUserId());
            if(optionalUser.isPresent())
                return new Device(deviceDetailsDTO.getId(), deviceDetailsDTO.getDescription(), deviceDetailsDTO.getAddress(),
                        deviceDetailsDTO.getMax_consumption(), deviceDetailsDTO.getAverage_consumption(), optionalUser.get());
        }
        return new Device(deviceDetailsDTO.getId(), deviceDetailsDTO.getDescription(),
                deviceDetailsDTO.getMax_consumption(), deviceDetailsDTO.getAverage_consumption()
                );
    }
}
