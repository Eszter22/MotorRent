package progkorny.motorrent.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import progkorny.motorrent.model.Motor;
import progkorny.motorrent.repository.MotorRepository;

class MotorServiceTest {
    @InjectMocks
    private MotorService motorservice;
    @Mock
    private MotorRepository MotorRepository;
    private Motor motor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.motor = new Motor();
        this.motor.setId(1L);
        this.motor.setName("Sea Breeze");
        this.motor.setBrand("Yamaha");
        this.motor.setLength((double)10.5F);
        this.motor.setModel("X100");
        this.motor.setBuildYear(2021);
        this.motor.setDailyRate((double)100.0F);
        this.motor.setAvailable(true);
        this.motor.setNumberOfSeats(5);
        this.motor.setVersion(1);
    }

    @Test
    void testGetAllMotors() {
        List<Motor> motors = List.of(this.motor);
        Mockito.when(this.MotorRepository.findAll()).thenReturn(motors);
        List<Motor> result = this.motorservice.getAllMotors();
        Assertions.assertEquals(1, result.size());
        ((MotorRepository)Mockito.verify(this.MotorRepository)).findAll();
    }

    @Test
    void testGetMotorById_Found() {
        Mockito.when(this.MotorRepository.findById(1L)).thenReturn(Optional.of(this.motor));
        Optional<Motor> result = this.motorservice.getMotorById(1L);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Sea Breeze", ((Motor)result.get()).getName());
    }

    @Test
    void testGetMotorById_NotFound() {
        Mockito.when(this.MotorRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Motor> result = this.motorservice.getMotorById(99L);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testCreateMotor() {
        Mockito.when((Motor)this.MotorRepository.save((Motor)Mockito.any(Motor.class))).thenReturn(this.motor);
        Motor result = this.motorservice.createMotor(this.motor);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Sea Breeze", result.getName());
        ((MotorRepository)Mockito.verify(this.MotorRepository)).save(this.motor);
    }

    @Test
    void testUpdateMotor_Success() {
        Motor updatedMotor = new Motor();
        updatedMotor.setName("Updated Name");
        updatedMotor.setBrand("Updated Brand");
        updatedMotor.setLength((double)11.0F);
        updatedMotor.setModel("Updated Model");
        updatedMotor.setBuildYear(2022);
        updatedMotor.setDailyRate((double)120.0F);
        updatedMotor.setAvailable(false);
        updatedMotor.setNumberOfSeats(6);
        updatedMotor.setVersion(2);
        Mockito.when(this.MotorRepository.findById(1L)).thenReturn(Optional.of(this.motor));
        Mockito.when((Motor)this.MotorRepository.save((Motor)Mockito.any(Motor.class))).thenAnswer((invocation) -> invocation.getArgument(0));
        Motor result = this.motorservice.updateMotor(1L, updatedMotor);
        Assertions.assertEquals("Updated Name", result.getName());
        Assertions.assertEquals("Updated Brand", result.getBrand());
        Assertions.assertEquals((double)11.0F, result.getLength());
        Assertions.assertEquals("Updated Model", result.getModel());
        Assertions.assertEquals(2022, result.getBuildYear());
        Assertions.assertEquals((double)120.0F, result.getDailyRate());
        Assertions.assertFalse(result.isAvailable());
        Assertions.assertEquals(6, result.getNumberOfSeats());
        Assertions.assertEquals(2, result.getVersion());
        ((MotorRepository)Mockito.verify(this.MotorRepository)).findById(1L);
        ((MotorRepository)Mockito.verify(this.MotorRepository)).save(result);
    }

    @Test
    void testUpdateMotor_NotFound() {
        Mockito.when(this.MotorRepository.findById(99L)).thenReturn(Optional.empty());
        Motor updatedMotor = new Motor();
        RuntimeException ex = (RuntimeException)Assertions.assertThrows(RuntimeException.class, () -> this.motorservice.updateMotor(99L, updatedMotor));
        Assertions.assertEquals("Motor not found", ex.getMessage());
    }

    @Test
    void testDeleteMotor_Exists() {
        Mockito.when(this.MotorRepository.existsById(1L)).thenReturn(true);
        ((MotorRepository)Mockito.doNothing().when(this.MotorRepository)).deleteById(1L);
        boolean result = this.motorservice.deleteMotor(1L);
        Assertions.assertTrue(result);
        ((MotorRepository)Mockito.verify(this.MotorRepository)).existsById(1L);
        ((MotorRepository)Mockito.verify(this.MotorRepository)).deleteById(1L);
    }

    @Test
    void testDeleteMotor_NotExists() {
        Mockito.when(this.MotorRepository.existsById(99L)).thenReturn(false);
        boolean result = this.motorservice.deleteMotor(99L);
        Assertions.assertFalse(result);
        ((MotorRepository)Mockito.verify(this.MotorRepository)).existsById(99L);
        ((MotorRepository)Mockito.verify(this.MotorRepository, Mockito.never())).deleteById(Mockito.anyLong());
    }
}
