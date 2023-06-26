package com.example.lab8_20176054.Controller;


import com.example.lab8_20176054.Entity.evento;
import com.example.lab8_20176054.Entity.tipoticket;
import com.example.lab8_20176054.Repository.EventoRepository;
import com.example.lab8_20176054.Repository.TipoTicketRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController //me añade el @ResponseBody
@CrossOrigin // para evitar bloqueos
@RequestMapping(value = "")
public class ControllerBase {

//    @Autowired
//    DaoEmpresa daoEmpresa;


    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    TipoTicketRepository tipoTicketRepository;
//
//    @Autowired
//    SupplierDao supplierDao;


    @GetMapping({"/evento"})
    public List<evento> listarEventos() {

        return eventoRepository.findAll();
    }


    @GetMapping("/evento/{id}")
    public ResponseEntity<HashMap<String, Object>> buscarxId(@PathVariable(value = "id", required = false) String idParam) {

        HashMap<String, Object> responseMap = new HashMap<>();

        try {
            int id = Integer.parseInt(idParam);
            if (id > 0) {
                Optional<evento> opt = eventoRepository.findById(id);
                if (opt.isPresent()) {
                    evento evento = opt.get();
                    responseMap.put("evento", evento);
                    responseMap.put("resultado", "exitoso");

                    return ResponseEntity.ok(responseMap);
                }else {

                    responseMap.put("msg", "Evento no encontrado");
                    responseMap.put("resultado", "Falla");
                    return ResponseEntity.badRequest().body(responseMap);
                }


                }

        } catch (NumberFormatException e) {
            responseMap.put("msg", "el ID debe ser un número entero positivo");
            responseMap.put("resultado", "Falla");
            return ResponseEntity.badRequest().body(responseMap);
        }
        return ResponseEntity.ok(responseMap);
    }




    @PostMapping("/evento")
    public ResponseEntity<HashMap<String, Object>> guardarEvento(
            @RequestBody evento evento,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseMap = new HashMap<>();


        eventoRepository.save(evento);

        if(fetchId){
            responseMap.put("id",evento.getId());
        }


        responseMap.put("estado", "creado");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionException(HttpServletRequest request){
        HashMap<String,String> responseMap = new HashMap<>();
        if(request.getMethod().equals("POST")|| request.getMethod().equals("PUT")){
            responseMap.put("estado","error");
            responseMap.put("msg","Debe enviar un evento");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }



    @GetMapping("/eventoConTipoDeTicket/{id}")
    public ResponseEntity<HashMap<String, Object>> buscarxTipoTicket(@PathVariable(value = "id", required = false) String idParam) {

        HashMap<String, Object> responseMap = new HashMap<>();

        try {
            int id = Integer.parseInt(idParam);
            if (id > 0) {
                Optional<tipoticket> opt = tipoTicketRepository.findById(id);
                if (opt.isPresent()) {
                    tipoticket tipoticket = opt.get();
                    responseMap.put("eventoConTipoDeTicket", tipoticket);
                    responseMap.put("resultado", "exitoso");

                    return ResponseEntity.ok(responseMap);
                }else {

                    responseMap.put("msg", "Tipo de Ticket no encontrado");
                    responseMap.put("resultado", "Falla");
                    return ResponseEntity.badRequest().body(responseMap);
                }


            }

        } catch (NumberFormatException e) {
            responseMap.put("msg", "el ID del Tipo de Ticket debe ser un número entero positivo");
            responseMap.put("resultado", "Falla");
            return ResponseEntity.badRequest().body(responseMap);
        }
        return ResponseEntity.ok(responseMap);
    }




    @PutMapping(value = "/evento")
    public ResponseEntity<HashMap<String, Object>> editarEvento(@RequestBody evento evento_2) {
        HashMap<String, Object> responseMap = new HashMap<>();

        if(evento_2.getId()>0){
            Optional<evento> opt = eventoRepository.findById(evento_2.getId());
            if(opt.isPresent()) {
                evento eventofromdb = opt.get();

                if (evento_2.getDescripcion() != null)
                    eventofromdb.setDescripcion(evento_2.getDescripcion());

                if (evento_2.getFecha() != null)
                    eventofromdb.setFecha(evento_2.getFecha());


                if (evento_2.getLocal() != null)
                    eventofromdb.setLocal(evento_2.getLocal());


                if (evento_2.getPath_image() != null)
                    eventofromdb.setPath_image(evento_2.getPath_image());


                eventoRepository.save(eventofromdb);
                responseMap.put("estado", "actualizado");

                return ResponseEntity.ok(responseMap);
            }else{
                responseMap.put("msg","El evento a actualizar no existe");
            }
        }else{
            responseMap.put("msg","Debe enviar un ID");
        }

        responseMap.put("estado","error");
        return ResponseEntity.badRequest().body(responseMap);
    }















//
//    @GetMapping("/delete")
//    public String borrarTransportista(Model model, @RequestParam("id") int id, RedirectAttributes attr) {
//
//        Product productBuscar = productDao.obtenerProductoPorId(id);
//
//        if (productBuscar != null) {
//            productDao.borrarProducto(id);
//            attr.addFlashAttribute("msg", "Producto borrado exitosamente");
//        }
//        return "redirect:/product";
//
//    }
}
