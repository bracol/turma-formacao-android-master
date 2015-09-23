package br.com.cast.turmaformacao.taskmanager.model.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrador on 15/09/2015.
 */
public class Task implements Parcelable {
    //os dados pode ser serializados, dados transmitos 1 a 1
    //parcelable forma mais rapida de serializar dados

    private Long id;
    private String name;
    private String description;
    private Label label;
    private Login usuario;

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

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Login getUsuario() {
        return usuario;
    }

    public void setUsuario(Login usuario) {
        this.usuario = usuario;
    }

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
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (getId() != null ? !getId().equals(task.getId()) : task.getId() != null) return false;
        if (getName() != null ? !getName().equals(task.getName()) : task.getName() != null)
            return false;
        return !(getDescription() != null ? !getDescription().equals(task.getDescription()) : task.getDescription() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", label = " + label.getId();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /* Escrita para o parcelable */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id == null ? -1 : id);
        dest.writeString(name == null ? "" : name);
        dest.writeString(description == null ? "" : description);
        dest.writeParcelable(label == null ? new Label() : label, flags);
        dest.writeParcelable(usuario == null ? new Login() : usuario, flags);
    }

    public void readFromParcel(Parcel imp) {
        id = imp.readLong();
        id = id == -1 ? null : id;

        name = imp.readString();
        description = imp.readString();
        label = imp.readParcelable(Label.class.getClassLoader());
        usuario = imp.readParcelable(Login.class.getClassLoader());
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
}
