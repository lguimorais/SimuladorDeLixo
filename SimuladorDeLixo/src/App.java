
import Mecanismo.Evento;
import Mecanismo.MotorSimulacao;
import Modelo.Zona;

public class App {
    public static void main(String[] args) throws Exception {
Zona zona = new Zona("zona1", 20, 40);
        MotorSimulacao motor = new MotorSimulacao();
        Evento gerarLixoZona1 = new Evento(0, "GERAR_LIXO", 1);
        motor.agendarEvento(gerarLixoZona1);

    }
}
