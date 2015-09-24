package br.com.cast.turmaformacao.taskmanager.model.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Administrador on 15/09/2015.
 */
public class Task implements Parcelable {
    //os dados pode ser serializados, dados transmitos 1 a 1
    //parcelable forma mais rapida de serializar dados

    private Long id;
    @JsonProperty("_id")
    private Long web_id;
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;
    private Label label;
    //private Login usuario;

    public Long getId() {
        return id;
    }

    public Task() {
        super();
    }

    public Task(Parcel imp) {
        super();
        readFromParcel(imp);
    }

    public Long getWeb_id() {
        return web_id;
    }

    public void setWeb_id(Long web_id) {
        this.web_id = web_id;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    /*public Login getUsuario() {
        return usuario;
    }

    public void setUsuario(Login usuario) {
        this.usuario = usuario;
    }*/

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (web_id != null ? !web_id.equals(task.web_id) : task.web_id != null) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null)
            return false;
        return !(label != null ? !label.equals(task.label) : task.label != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (web_id != null ? web_id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /* Escrita para o parcelable */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id == null ? -1 : id);
        dest.writeLong(web_id == null ? -1 : web_id);
        dest.writeString(name == null ? "" : name);
        dest.writeString(description == null ? "" : description);
        //dest.writeParcelable(label == null ? new Label() : label, flags);

        //dest.writeParcelable(usuario == null ? new Login() : usuario, flags);
    }

    public void readFromParcel(Parcel imp) {
        id = imp.readLong();
        id = id == -1 ? null : id;

        web_id = imp.readLong();
        web_id = id == -1 ? null : id;

        name = imp.readString();
        description = imp.readString();
        label = imp.readParcelable(Label.class.getClassLoader());
        //usuario = imp.readParcelable(Login.class.getClassLoader());
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {

        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", web_id=" + web_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", label=" + label +
                //", usuario=" + usuario +
                '}';
    }
}
