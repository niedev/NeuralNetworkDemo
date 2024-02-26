// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: sentencepiece.proto

#include "sentencepiece.pb.h"

#include <algorithm>

#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/extension_set.h>
#include <google/protobuf/wire_format_lite.h>
#include <google/protobuf/io/zero_copy_stream_impl_lite.h>
// @@protoc_insertion_point(includes)
#include <google/protobuf/port_def.inc>
extern PROTOBUF_INTERNAL_EXPORT_sentencepiece_2eproto ::PROTOBUF_NAMESPACE_ID::internal::SCCInfo<1> scc_info_SentencePieceText_sentencepiece_2eproto;
extern PROTOBUF_INTERNAL_EXPORT_sentencepiece_2eproto ::PROTOBUF_NAMESPACE_ID::internal::SCCInfo<0> scc_info_SentencePieceText_SentencePiece_sentencepiece_2eproto;
namespace sentencepiece {
class SentencePieceText_SentencePieceDefaultTypeInternal {
 public:
  ::PROTOBUF_NAMESPACE_ID::internal::ExplicitlyConstructed<SentencePieceText_SentencePiece> _instance;
} _SentencePieceText_SentencePiece_default_instance_;
class SentencePieceTextDefaultTypeInternal {
 public:
  ::PROTOBUF_NAMESPACE_ID::internal::ExplicitlyConstructed<SentencePieceText> _instance;
} _SentencePieceText_default_instance_;
class NBestSentencePieceTextDefaultTypeInternal {
 public:
  ::PROTOBUF_NAMESPACE_ID::internal::ExplicitlyConstructed<NBestSentencePieceText> _instance;
} _NBestSentencePieceText_default_instance_;
}  // namespace sentencepiece
static void InitDefaultsscc_info_NBestSentencePieceText_sentencepiece_2eproto() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  {
    void* ptr = &::sentencepiece::_NBestSentencePieceText_default_instance_;
    new (ptr) ::sentencepiece::NBestSentencePieceText();
    ::PROTOBUF_NAMESPACE_ID::internal::OnShutdownDestroyMessage(ptr);
  }
}

::PROTOBUF_NAMESPACE_ID::internal::SCCInfo<1> scc_info_NBestSentencePieceText_sentencepiece_2eproto =
    {{ATOMIC_VAR_INIT(::PROTOBUF_NAMESPACE_ID::internal::SCCInfoBase::kUninitialized), 1, 0, InitDefaultsscc_info_NBestSentencePieceText_sentencepiece_2eproto}, {
      &scc_info_SentencePieceText_sentencepiece_2eproto.base,}};

static void InitDefaultsscc_info_SentencePieceText_sentencepiece_2eproto() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  {
    void* ptr = &::sentencepiece::_SentencePieceText_default_instance_;
    new (ptr) ::sentencepiece::SentencePieceText();
    ::PROTOBUF_NAMESPACE_ID::internal::OnShutdownDestroyMessage(ptr);
  }
}

::PROTOBUF_NAMESPACE_ID::internal::SCCInfo<1> scc_info_SentencePieceText_sentencepiece_2eproto =
    {{ATOMIC_VAR_INIT(::PROTOBUF_NAMESPACE_ID::internal::SCCInfoBase::kUninitialized), 1, 0, InitDefaultsscc_info_SentencePieceText_sentencepiece_2eproto}, {
      &scc_info_SentencePieceText_SentencePiece_sentencepiece_2eproto.base,}};

static void InitDefaultsscc_info_SentencePieceText_SentencePiece_sentencepiece_2eproto() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  {
    void* ptr = &::sentencepiece::_SentencePieceText_SentencePiece_default_instance_;
    new (ptr) ::sentencepiece::SentencePieceText_SentencePiece();
    ::PROTOBUF_NAMESPACE_ID::internal::OnShutdownDestroyMessage(ptr);
  }
}

::PROTOBUF_NAMESPACE_ID::internal::SCCInfo<0> scc_info_SentencePieceText_SentencePiece_sentencepiece_2eproto =
    {{ATOMIC_VAR_INIT(::PROTOBUF_NAMESPACE_ID::internal::SCCInfoBase::kUninitialized), 0, 0, InitDefaultsscc_info_SentencePieceText_SentencePiece_sentencepiece_2eproto}, {}};

namespace sentencepiece {

// ===================================================================

class SentencePieceText_SentencePiece::_Internal {
 public:
  using HasBits = decltype(std::declval<SentencePieceText_SentencePiece>()._has_bits_);
  static void set_has_piece(HasBits* has_bits) {
    (*has_bits)[0] |= 1u;
  }
  static void set_has_id(HasBits* has_bits) {
    (*has_bits)[0] |= 4u;
  }
  static void set_has_surface(HasBits* has_bits) {
    (*has_bits)[0] |= 2u;
  }
  static void set_has_begin(HasBits* has_bits) {
    (*has_bits)[0] |= 8u;
  }
  static void set_has_end(HasBits* has_bits) {
    (*has_bits)[0] |= 16u;
  }
};

SentencePieceText_SentencePiece::SentencePieceText_SentencePiece(::PROTOBUF_NAMESPACE_ID::Arena* arena)
  : ::PROTOBUF_NAMESPACE_ID::MessageLite(arena),
  _extensions_(arena) {
  SharedCtor();
  RegisterArenaDtor(arena);
  // @@protoc_insertion_point(arena_constructor:sentencepiece.SentencePieceText.SentencePiece)
}
SentencePieceText_SentencePiece::SentencePieceText_SentencePiece(const SentencePieceText_SentencePiece& from)
  : ::PROTOBUF_NAMESPACE_ID::MessageLite(),
      _has_bits_(from._has_bits_) {
  _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
  _extensions_.MergeFrom(from._extensions_);
  piece_.UnsafeSetDefault(&::PROTOBUF_NAMESPACE_ID::internal::GetEmptyStringAlreadyInited());
  if (from._internal_has_piece()) {
    piece_.Set(::PROTOBUF_NAMESPACE_ID::internal::ArenaStringPtr::EmptyDefault{}, from._internal_piece(), 
      GetArena());
  }
  surface_.UnsafeSetDefault(&::PROTOBUF_NAMESPACE_ID::internal::GetEmptyStringAlreadyInited());
  if (from._internal_has_surface()) {
    surface_.Set(::PROTOBUF_NAMESPACE_ID::internal::ArenaStringPtr::EmptyDefault{}, from._internal_surface(), 
      GetArena());
  }
  ::memcpy(&id_, &from.id_,
    static_cast<size_t>(reinterpret_cast<char*>(&end_) -
    reinterpret_cast<char*>(&id_)) + sizeof(end_));
  // @@protoc_insertion_point(copy_constructor:sentencepiece.SentencePieceText.SentencePiece)
}

void SentencePieceText_SentencePiece::SharedCtor() {
  ::PROTOBUF_NAMESPACE_ID::internal::InitSCC(&scc_info_SentencePieceText_SentencePiece_sentencepiece_2eproto.base);
  piece_.UnsafeSetDefault(&::PROTOBUF_NAMESPACE_ID::internal::GetEmptyStringAlreadyInited());
  surface_.UnsafeSetDefault(&::PROTOBUF_NAMESPACE_ID::internal::GetEmptyStringAlreadyInited());
  ::memset(reinterpret_cast<char*>(this) + static_cast<size_t>(
      reinterpret_cast<char*>(&id_) - reinterpret_cast<char*>(this)),
      0, static_cast<size_t>(reinterpret_cast<char*>(&end_) -
      reinterpret_cast<char*>(&id_)) + sizeof(end_));
}

SentencePieceText_SentencePiece::~SentencePieceText_SentencePiece() {
  // @@protoc_insertion_point(destructor:sentencepiece.SentencePieceText.SentencePiece)
  SharedDtor();
  _internal_metadata_.Delete<std::string>();
}

void SentencePieceText_SentencePiece::SharedDtor() {
  GOOGLE_DCHECK(GetArena() == nullptr);
  piece_.DestroyNoArena(&::PROTOBUF_NAMESPACE_ID::internal::GetEmptyStringAlreadyInited());
  surface_.DestroyNoArena(&::PROTOBUF_NAMESPACE_ID::internal::GetEmptyStringAlreadyInited());
}

void SentencePieceText_SentencePiece::ArenaDtor(void* object) {
  SentencePieceText_SentencePiece* _this = reinterpret_cast< SentencePieceText_SentencePiece* >(object);
  (void)_this;
}
void SentencePieceText_SentencePiece::RegisterArenaDtor(::PROTOBUF_NAMESPACE_ID::Arena*) {
}
void SentencePieceText_SentencePiece::SetCachedSize(int size) const {
  _cached_size_.Set(size);
}
const SentencePieceText_SentencePiece& SentencePieceText_SentencePiece::default_instance() {
  ::PROTOBUF_NAMESPACE_ID::internal::InitSCC(&::scc_info_SentencePieceText_SentencePiece_sentencepiece_2eproto.base);
  return *internal_default_instance();
}


void SentencePieceText_SentencePiece::Clear() {
// @@protoc_insertion_point(message_clear_start:sentencepiece.SentencePieceText.SentencePiece)
  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  _extensions_.Clear();
  cached_has_bits = _has_bits_[0];
  if (cached_has_bits & 0x00000003u) {
    if (cached_has_bits & 0x00000001u) {
      piece_.ClearNonDefaultToEmpty();
    }
    if (cached_has_bits & 0x00000002u) {
      surface_.ClearNonDefaultToEmpty();
    }
  }
  if (cached_has_bits & 0x0000001cu) {
    ::memset(&id_, 0, static_cast<size_t>(
        reinterpret_cast<char*>(&end_) -
        reinterpret_cast<char*>(&id_)) + sizeof(end_));
  }
  _has_bits_.Clear();
  _internal_metadata_.Clear<std::string>();
}

const char* SentencePieceText_SentencePiece::_InternalParse(const char* ptr, ::PROTOBUF_NAMESPACE_ID::internal::ParseContext* ctx) {
#define CHK_(x) if (PROTOBUF_PREDICT_FALSE(!(x))) goto failure
  _Internal::HasBits has_bits{};
  while (!ctx->Done(&ptr)) {
    ::PROTOBUF_NAMESPACE_ID::uint32 tag;
    ptr = ::PROTOBUF_NAMESPACE_ID::internal::ReadTag(ptr, &tag);
    CHK_(ptr);
    switch (tag >> 3) {
      // optional string piece = 1;
      case 1:
        if (PROTOBUF_PREDICT_TRUE(static_cast<::PROTOBUF_NAMESPACE_ID::uint8>(tag) == 10)) {
          auto str = _internal_mutable_piece();
          ptr = ::PROTOBUF_NAMESPACE_ID::internal::InlineGreedyStringParser(str, ptr, ctx);
          CHK_(ptr);
        } else goto handle_unusual;
        continue;
      // optional uint32 id = 2;
      case 2:
        if (PROTOBUF_PREDICT_TRUE(static_cast<::PROTOBUF_NAMESPACE_ID::uint8>(tag) == 16)) {
          _Internal::set_has_id(&has_bits);
          id_ = ::PROTOBUF_NAMESPACE_ID::internal::ReadVarint32(&ptr);
          CHK_(ptr);
        } else goto handle_unusual;
        continue;
      // optional string surface = 3;
      case 3:
        if (PROTOBUF_PREDICT_TRUE(static_cast<::PROTOBUF_NAMESPACE_ID::uint8>(tag) == 26)) {
          auto str = _internal_mutable_surface();
          ptr = ::PROTOBUF_NAMESPACE_ID::internal::InlineGreedyStringParser(str, ptr, ctx);
          CHK_(ptr);
        } else goto handle_unusual;
        continue;
      // optional uint32 begin = 4;
      case 4:
        if (PROTOBUF_PREDICT_TRUE(static_cast<::PROTOBUF_NAMESPACE_ID::uint8>(tag) == 32)) {
          _Internal::set_has_begin(&has_bits);
          begin_ = ::PROTOBUF_NAMESPACE_ID::internal::ReadVarint32(&ptr);
          CHK_(ptr);
        } else goto handle_unusual;
        continue;
      // optional uint32 end = 5;
      case 5:
        if (PROTOBUF_PREDICT_TRUE(static_cast<::PROTOBUF_NAMESPACE_ID::uint8>(tag) == 40)) {
          _Internal::set_has_end(&has_bits);
          end_ = ::PROTOBUF_NAMESPACE_ID::internal::ReadVarint32(&ptr);
          CHK_(ptr);
        } else goto handle_unusual;
        continue;
      default: {
      handle_unusual:
        if ((tag & 7) == 4 || tag == 0) {
          ctx->SetLastTag(tag);
          goto success;
        }
      if ((1600u <= tag)) {
        ptr = _extensions_.ParseField(tag, ptr,
            internal_default_instance(), &_internal_metadata_, ctx);
        CHK_(ptr != nullptr);
        continue;
      }
        ptr = UnknownFieldParse(tag,
            _internal_metadata_.mutable_unknown_fields<std::string>(),
            ptr, ctx);
        CHK_(ptr != nullptr);
        continue;
      }
    }  // switch
  }  // while
success:
  _has_bits_.Or(has_bits);
  return ptr;
failure:
  ptr = nullptr;
  goto success;
#undef CHK_
}

::PROTOBUF_NAMESPACE_ID::uint8* SentencePieceText_SentencePiece::_InternalSerialize(
    ::PROTOBUF_NAMESPACE_ID::uint8* target, ::PROTOBUF_NAMESPACE_ID::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:sentencepiece.SentencePieceText.SentencePiece)
  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  (void) cached_has_bits;

  cached_has_bits = _has_bits_[0];
  // optional string piece = 1;
  if (cached_has_bits & 0x00000001u) {
    target = stream->WriteStringMaybeAliased(
        1, this->_internal_piece(), target);
  }

  // optional uint32 id = 2;
  if (cached_has_bits & 0x00000004u) {
    target = stream->EnsureSpace(target);
    target = ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::WriteUInt32ToArray(2, this->_internal_id(), target);
  }

  // optional string surface = 3;
  if (cached_has_bits & 0x00000002u) {
    target = stream->WriteStringMaybeAliased(
        3, this->_internal_surface(), target);
  }

  // optional uint32 begin = 4;
  if (cached_has_bits & 0x00000008u) {
    target = stream->EnsureSpace(target);
    target = ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::WriteUInt32ToArray(4, this->_internal_begin(), target);
  }

  // optional uint32 end = 5;
  if (cached_has_bits & 0x00000010u) {
    target = stream->EnsureSpace(target);
    target = ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::WriteUInt32ToArray(5, this->_internal_end(), target);
  }

  // Extension range [200, 536870912)
  target = _extensions_._InternalSerialize(
      200, 536870912, target, stream);

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = stream->WriteRaw(_internal_metadata_.unknown_fields<std::string>(::PROTOBUF_NAMESPACE_ID::internal::GetEmptyString).data(),
        static_cast<int>(_internal_metadata_.unknown_fields<std::string>(::PROTOBUF_NAMESPACE_ID::internal::GetEmptyString).size()), target);
  }
  // @@protoc_insertion_point(serialize_to_array_end:sentencepiece.SentencePieceText.SentencePiece)
  return target;
}

size_t SentencePieceText_SentencePiece::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:sentencepiece.SentencePieceText.SentencePiece)
  size_t total_size = 0;

  total_size += _extensions_.ByteSize();

  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  cached_has_bits = _has_bits_[0];
  if (cached_has_bits & 0x0000001fu) {
    // optional string piece = 1;
    if (cached_has_bits & 0x00000001u) {
      total_size += 1 +
        ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::StringSize(
          this->_internal_piece());
    }

    // optional string surface = 3;
    if (cached_has_bits & 0x00000002u) {
      total_size += 1 +
        ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::StringSize(
          this->_internal_surface());
    }

    // optional uint32 id = 2;
    if (cached_has_bits & 0x00000004u) {
      total_size += 1 +
        ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::UInt32Size(
          this->_internal_id());
    }

    // optional uint32 begin = 4;
    if (cached_has_bits & 0x00000008u) {
      total_size += 1 +
        ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::UInt32Size(
          this->_internal_begin());
    }

    // optional uint32 end = 5;
    if (cached_has_bits & 0x00000010u) {
      total_size += 1 +
        ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::UInt32Size(
          this->_internal_end());
    }

  }
  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    total_size += _internal_metadata_.unknown_fields<std::string>(::PROTOBUF_NAMESPACE_ID::internal::GetEmptyString).size();
  }
  int cached_size = ::PROTOBUF_NAMESPACE_ID::internal::ToCachedSize(total_size);
  SetCachedSize(cached_size);
  return total_size;
}

void SentencePieceText_SentencePiece::CheckTypeAndMergeFrom(
    const ::PROTOBUF_NAMESPACE_ID::MessageLite& from) {
  MergeFrom(*::PROTOBUF_NAMESPACE_ID::internal::DownCast<const SentencePieceText_SentencePiece*>(
      &from));
}

void SentencePieceText_SentencePiece::MergeFrom(const SentencePieceText_SentencePiece& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:sentencepiece.SentencePieceText.SentencePiece)
  GOOGLE_DCHECK_NE(&from, this);
  _extensions_.MergeFrom(from._extensions_);
  _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  (void) cached_has_bits;

  cached_has_bits = from._has_bits_[0];
  if (cached_has_bits & 0x0000001fu) {
    if (cached_has_bits & 0x00000001u) {
      _internal_set_piece(from._internal_piece());
    }
    if (cached_has_bits & 0x00000002u) {
      _internal_set_surface(from._internal_surface());
    }
    if (cached_has_bits & 0x00000004u) {
      id_ = from.id_;
    }
    if (cached_has_bits & 0x00000008u) {
      begin_ = from.begin_;
    }
    if (cached_has_bits & 0x00000010u) {
      end_ = from.end_;
    }
    _has_bits_[0] |= cached_has_bits;
  }
}

void SentencePieceText_SentencePiece::CopyFrom(const SentencePieceText_SentencePiece& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:sentencepiece.SentencePieceText.SentencePiece)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool SentencePieceText_SentencePiece::IsInitialized() const {
  if (!_extensions_.IsInitialized()) {
    return false;
  }

  return true;
}

void SentencePieceText_SentencePiece::InternalSwap(SentencePieceText_SentencePiece* other) {
  using std::swap;
  _extensions_.Swap(&other->_extensions_);
  _internal_metadata_.Swap<std::string>(&other->_internal_metadata_);
  swap(_has_bits_[0], other->_has_bits_[0]);
  piece_.Swap(&other->piece_, &::PROTOBUF_NAMESPACE_ID::internal::GetEmptyStringAlreadyInited(), GetArena());
  surface_.Swap(&other->surface_, &::PROTOBUF_NAMESPACE_ID::internal::GetEmptyStringAlreadyInited(), GetArena());
  ::PROTOBUF_NAMESPACE_ID::internal::memswap<
      PROTOBUF_FIELD_OFFSET(SentencePieceText_SentencePiece, end_)
      + sizeof(SentencePieceText_SentencePiece::end_)
      - PROTOBUF_FIELD_OFFSET(SentencePieceText_SentencePiece, id_)>(
          reinterpret_cast<char*>(&id_),
          reinterpret_cast<char*>(&other->id_));
}

std::string SentencePieceText_SentencePiece::GetTypeName() const {
  return "sentencepiece.SentencePieceText.SentencePiece";
}


// ===================================================================

class SentencePieceText::_Internal {
 public:
  using HasBits = decltype(std::declval<SentencePieceText>()._has_bits_);
  static void set_has_text(HasBits* has_bits) {
    (*has_bits)[0] |= 1u;
  }
  static void set_has_score(HasBits* has_bits) {
    (*has_bits)[0] |= 2u;
  }
};

SentencePieceText::SentencePieceText(::PROTOBUF_NAMESPACE_ID::Arena* arena)
  : ::PROTOBUF_NAMESPACE_ID::MessageLite(arena),
  _extensions_(arena),
  pieces_(arena) {
  SharedCtor();
  RegisterArenaDtor(arena);
  // @@protoc_insertion_point(arena_constructor:sentencepiece.SentencePieceText)
}
SentencePieceText::SentencePieceText(const SentencePieceText& from)
  : ::PROTOBUF_NAMESPACE_ID::MessageLite(),
      _has_bits_(from._has_bits_),
      pieces_(from.pieces_) {
  _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
  _extensions_.MergeFrom(from._extensions_);
  text_.UnsafeSetDefault(&::PROTOBUF_NAMESPACE_ID::internal::GetEmptyStringAlreadyInited());
  if (from._internal_has_text()) {
    text_.Set(::PROTOBUF_NAMESPACE_ID::internal::ArenaStringPtr::EmptyDefault{}, from._internal_text(), 
      GetArena());
  }
  score_ = from.score_;
  // @@protoc_insertion_point(copy_constructor:sentencepiece.SentencePieceText)
}

void SentencePieceText::SharedCtor() {
  ::PROTOBUF_NAMESPACE_ID::internal::InitSCC(&scc_info_SentencePieceText_sentencepiece_2eproto.base);
  text_.UnsafeSetDefault(&::PROTOBUF_NAMESPACE_ID::internal::GetEmptyStringAlreadyInited());
  score_ = 0;
}

SentencePieceText::~SentencePieceText() {
  // @@protoc_insertion_point(destructor:sentencepiece.SentencePieceText)
  SharedDtor();
  _internal_metadata_.Delete<std::string>();
}

void SentencePieceText::SharedDtor() {
  GOOGLE_DCHECK(GetArena() == nullptr);
  text_.DestroyNoArena(&::PROTOBUF_NAMESPACE_ID::internal::GetEmptyStringAlreadyInited());
}

void SentencePieceText::ArenaDtor(void* object) {
  SentencePieceText* _this = reinterpret_cast< SentencePieceText* >(object);
  (void)_this;
}
void SentencePieceText::RegisterArenaDtor(::PROTOBUF_NAMESPACE_ID::Arena*) {
}
void SentencePieceText::SetCachedSize(int size) const {
  _cached_size_.Set(size);
}
const SentencePieceText& SentencePieceText::default_instance() {
  ::PROTOBUF_NAMESPACE_ID::internal::InitSCC(&::scc_info_SentencePieceText_sentencepiece_2eproto.base);
  return *internal_default_instance();
}


void SentencePieceText::Clear() {
// @@protoc_insertion_point(message_clear_start:sentencepiece.SentencePieceText)
  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  _extensions_.Clear();
  pieces_.Clear();
  cached_has_bits = _has_bits_[0];
  if (cached_has_bits & 0x00000001u) {
    text_.ClearNonDefaultToEmpty();
  }
  score_ = 0;
  _has_bits_.Clear();
  _internal_metadata_.Clear<std::string>();
}

const char* SentencePieceText::_InternalParse(const char* ptr, ::PROTOBUF_NAMESPACE_ID::internal::ParseContext* ctx) {
#define CHK_(x) if (PROTOBUF_PREDICT_FALSE(!(x))) goto failure
  _Internal::HasBits has_bits{};
  while (!ctx->Done(&ptr)) {
    ::PROTOBUF_NAMESPACE_ID::uint32 tag;
    ptr = ::PROTOBUF_NAMESPACE_ID::internal::ReadTag(ptr, &tag);
    CHK_(ptr);
    switch (tag >> 3) {
      // optional string text = 1;
      case 1:
        if (PROTOBUF_PREDICT_TRUE(static_cast<::PROTOBUF_NAMESPACE_ID::uint8>(tag) == 10)) {
          auto str = _internal_mutable_text();
          ptr = ::PROTOBUF_NAMESPACE_ID::internal::InlineGreedyStringParser(str, ptr, ctx);
          CHK_(ptr);
        } else goto handle_unusual;
        continue;
      // repeated .sentencepiece.SentencePieceText.SentencePiece pieces = 2;
      case 2:
        if (PROTOBUF_PREDICT_TRUE(static_cast<::PROTOBUF_NAMESPACE_ID::uint8>(tag) == 18)) {
          ptr -= 1;
          do {
            ptr += 1;
            ptr = ctx->ParseMessage(_internal_add_pieces(), ptr);
            CHK_(ptr);
            if (!ctx->DataAvailable(ptr)) break;
          } while (::PROTOBUF_NAMESPACE_ID::internal::ExpectTag<18>(ptr));
        } else goto handle_unusual;
        continue;
      // optional float score = 3;
      case 3:
        if (PROTOBUF_PREDICT_TRUE(static_cast<::PROTOBUF_NAMESPACE_ID::uint8>(tag) == 29)) {
          _Internal::set_has_score(&has_bits);
          score_ = ::PROTOBUF_NAMESPACE_ID::internal::UnalignedLoad<float>(ptr);
          ptr += sizeof(float);
        } else goto handle_unusual;
        continue;
      default: {
      handle_unusual:
        if ((tag & 7) == 4 || tag == 0) {
          ctx->SetLastTag(tag);
          goto success;
        }
      if ((1600u <= tag)) {
        ptr = _extensions_.ParseField(tag, ptr,
            internal_default_instance(), &_internal_metadata_, ctx);
        CHK_(ptr != nullptr);
        continue;
      }
        ptr = UnknownFieldParse(tag,
            _internal_metadata_.mutable_unknown_fields<std::string>(),
            ptr, ctx);
        CHK_(ptr != nullptr);
        continue;
      }
    }  // switch
  }  // while
success:
  _has_bits_.Or(has_bits);
  return ptr;
failure:
  ptr = nullptr;
  goto success;
#undef CHK_
}

::PROTOBUF_NAMESPACE_ID::uint8* SentencePieceText::_InternalSerialize(
    ::PROTOBUF_NAMESPACE_ID::uint8* target, ::PROTOBUF_NAMESPACE_ID::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:sentencepiece.SentencePieceText)
  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  (void) cached_has_bits;

  cached_has_bits = _has_bits_[0];
  // optional string text = 1;
  if (cached_has_bits & 0x00000001u) {
    target = stream->WriteStringMaybeAliased(
        1, this->_internal_text(), target);
  }

  // repeated .sentencepiece.SentencePieceText.SentencePiece pieces = 2;
  for (unsigned int i = 0,
      n = static_cast<unsigned int>(this->_internal_pieces_size()); i < n; i++) {
    target = stream->EnsureSpace(target);
    target = ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::
      InternalWriteMessage(2, this->_internal_pieces(i), target, stream);
  }

  // optional float score = 3;
  if (cached_has_bits & 0x00000002u) {
    target = stream->EnsureSpace(target);
    target = ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::WriteFloatToArray(3, this->_internal_score(), target);
  }

  // Extension range [200, 536870912)
  target = _extensions_._InternalSerialize(
      200, 536870912, target, stream);

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = stream->WriteRaw(_internal_metadata_.unknown_fields<std::string>(::PROTOBUF_NAMESPACE_ID::internal::GetEmptyString).data(),
        static_cast<int>(_internal_metadata_.unknown_fields<std::string>(::PROTOBUF_NAMESPACE_ID::internal::GetEmptyString).size()), target);
  }
  // @@protoc_insertion_point(serialize_to_array_end:sentencepiece.SentencePieceText)
  return target;
}

size_t SentencePieceText::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:sentencepiece.SentencePieceText)
  size_t total_size = 0;

  total_size += _extensions_.ByteSize();

  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  // repeated .sentencepiece.SentencePieceText.SentencePiece pieces = 2;
  total_size += 1UL * this->_internal_pieces_size();
  for (const auto& msg : this->pieces_) {
    total_size +=
      ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::MessageSize(msg);
  }

  cached_has_bits = _has_bits_[0];
  if (cached_has_bits & 0x00000003u) {
    // optional string text = 1;
    if (cached_has_bits & 0x00000001u) {
      total_size += 1 +
        ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::StringSize(
          this->_internal_text());
    }

    // optional float score = 3;
    if (cached_has_bits & 0x00000002u) {
      total_size += 1 + 4;
    }

  }
  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    total_size += _internal_metadata_.unknown_fields<std::string>(::PROTOBUF_NAMESPACE_ID::internal::GetEmptyString).size();
  }
  int cached_size = ::PROTOBUF_NAMESPACE_ID::internal::ToCachedSize(total_size);
  SetCachedSize(cached_size);
  return total_size;
}

void SentencePieceText::CheckTypeAndMergeFrom(
    const ::PROTOBUF_NAMESPACE_ID::MessageLite& from) {
  MergeFrom(*::PROTOBUF_NAMESPACE_ID::internal::DownCast<const SentencePieceText*>(
      &from));
}

void SentencePieceText::MergeFrom(const SentencePieceText& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:sentencepiece.SentencePieceText)
  GOOGLE_DCHECK_NE(&from, this);
  _extensions_.MergeFrom(from._extensions_);
  _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  (void) cached_has_bits;

  pieces_.MergeFrom(from.pieces_);
  cached_has_bits = from._has_bits_[0];
  if (cached_has_bits & 0x00000003u) {
    if (cached_has_bits & 0x00000001u) {
      _internal_set_text(from._internal_text());
    }
    if (cached_has_bits & 0x00000002u) {
      score_ = from.score_;
    }
    _has_bits_[0] |= cached_has_bits;
  }
}

void SentencePieceText::CopyFrom(const SentencePieceText& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:sentencepiece.SentencePieceText)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool SentencePieceText::IsInitialized() const {
  if (!_extensions_.IsInitialized()) {
    return false;
  }

  if (!::PROTOBUF_NAMESPACE_ID::internal::AllAreInitialized(pieces_)) return false;
  return true;
}

void SentencePieceText::InternalSwap(SentencePieceText* other) {
  using std::swap;
  _extensions_.Swap(&other->_extensions_);
  _internal_metadata_.Swap<std::string>(&other->_internal_metadata_);
  swap(_has_bits_[0], other->_has_bits_[0]);
  pieces_.InternalSwap(&other->pieces_);
  text_.Swap(&other->text_, &::PROTOBUF_NAMESPACE_ID::internal::GetEmptyStringAlreadyInited(), GetArena());
  swap(score_, other->score_);
}

std::string SentencePieceText::GetTypeName() const {
  return "sentencepiece.SentencePieceText";
}


// ===================================================================

class NBestSentencePieceText::_Internal {
 public:
};

NBestSentencePieceText::NBestSentencePieceText(::PROTOBUF_NAMESPACE_ID::Arena* arena)
  : ::PROTOBUF_NAMESPACE_ID::MessageLite(arena),
  nbests_(arena) {
  SharedCtor();
  RegisterArenaDtor(arena);
  // @@protoc_insertion_point(arena_constructor:sentencepiece.NBestSentencePieceText)
}
NBestSentencePieceText::NBestSentencePieceText(const NBestSentencePieceText& from)
  : ::PROTOBUF_NAMESPACE_ID::MessageLite(),
      nbests_(from.nbests_) {
  _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
  // @@protoc_insertion_point(copy_constructor:sentencepiece.NBestSentencePieceText)
}

void NBestSentencePieceText::SharedCtor() {
  ::PROTOBUF_NAMESPACE_ID::internal::InitSCC(&scc_info_NBestSentencePieceText_sentencepiece_2eproto.base);
}

NBestSentencePieceText::~NBestSentencePieceText() {
  // @@protoc_insertion_point(destructor:sentencepiece.NBestSentencePieceText)
  SharedDtor();
  _internal_metadata_.Delete<std::string>();
}

void NBestSentencePieceText::SharedDtor() {
  GOOGLE_DCHECK(GetArena() == nullptr);
}

void NBestSentencePieceText::ArenaDtor(void* object) {
  NBestSentencePieceText* _this = reinterpret_cast< NBestSentencePieceText* >(object);
  (void)_this;
}
void NBestSentencePieceText::RegisterArenaDtor(::PROTOBUF_NAMESPACE_ID::Arena*) {
}
void NBestSentencePieceText::SetCachedSize(int size) const {
  _cached_size_.Set(size);
}
const NBestSentencePieceText& NBestSentencePieceText::default_instance() {
  ::PROTOBUF_NAMESPACE_ID::internal::InitSCC(&::scc_info_NBestSentencePieceText_sentencepiece_2eproto.base);
  return *internal_default_instance();
}


void NBestSentencePieceText::Clear() {
// @@protoc_insertion_point(message_clear_start:sentencepiece.NBestSentencePieceText)
  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  nbests_.Clear();
  _internal_metadata_.Clear<std::string>();
}

const char* NBestSentencePieceText::_InternalParse(const char* ptr, ::PROTOBUF_NAMESPACE_ID::internal::ParseContext* ctx) {
#define CHK_(x) if (PROTOBUF_PREDICT_FALSE(!(x))) goto failure
  while (!ctx->Done(&ptr)) {
    ::PROTOBUF_NAMESPACE_ID::uint32 tag;
    ptr = ::PROTOBUF_NAMESPACE_ID::internal::ReadTag(ptr, &tag);
    CHK_(ptr);
    switch (tag >> 3) {
      // repeated .sentencepiece.SentencePieceText nbests = 1;
      case 1:
        if (PROTOBUF_PREDICT_TRUE(static_cast<::PROTOBUF_NAMESPACE_ID::uint8>(tag) == 10)) {
          ptr -= 1;
          do {
            ptr += 1;
            ptr = ctx->ParseMessage(_internal_add_nbests(), ptr);
            CHK_(ptr);
            if (!ctx->DataAvailable(ptr)) break;
          } while (::PROTOBUF_NAMESPACE_ID::internal::ExpectTag<10>(ptr));
        } else goto handle_unusual;
        continue;
      default: {
      handle_unusual:
        if ((tag & 7) == 4 || tag == 0) {
          ctx->SetLastTag(tag);
          goto success;
        }
        ptr = UnknownFieldParse(tag,
            _internal_metadata_.mutable_unknown_fields<std::string>(),
            ptr, ctx);
        CHK_(ptr != nullptr);
        continue;
      }
    }  // switch
  }  // while
success:
  return ptr;
failure:
  ptr = nullptr;
  goto success;
#undef CHK_
}

::PROTOBUF_NAMESPACE_ID::uint8* NBestSentencePieceText::_InternalSerialize(
    ::PROTOBUF_NAMESPACE_ID::uint8* target, ::PROTOBUF_NAMESPACE_ID::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:sentencepiece.NBestSentencePieceText)
  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  (void) cached_has_bits;

  // repeated .sentencepiece.SentencePieceText nbests = 1;
  for (unsigned int i = 0,
      n = static_cast<unsigned int>(this->_internal_nbests_size()); i < n; i++) {
    target = stream->EnsureSpace(target);
    target = ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::
      InternalWriteMessage(1, this->_internal_nbests(i), target, stream);
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = stream->WriteRaw(_internal_metadata_.unknown_fields<std::string>(::PROTOBUF_NAMESPACE_ID::internal::GetEmptyString).data(),
        static_cast<int>(_internal_metadata_.unknown_fields<std::string>(::PROTOBUF_NAMESPACE_ID::internal::GetEmptyString).size()), target);
  }
  // @@protoc_insertion_point(serialize_to_array_end:sentencepiece.NBestSentencePieceText)
  return target;
}

size_t NBestSentencePieceText::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:sentencepiece.NBestSentencePieceText)
  size_t total_size = 0;

  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  // repeated .sentencepiece.SentencePieceText nbests = 1;
  total_size += 1UL * this->_internal_nbests_size();
  for (const auto& msg : this->nbests_) {
    total_size +=
      ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::MessageSize(msg);
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    total_size += _internal_metadata_.unknown_fields<std::string>(::PROTOBUF_NAMESPACE_ID::internal::GetEmptyString).size();
  }
  int cached_size = ::PROTOBUF_NAMESPACE_ID::internal::ToCachedSize(total_size);
  SetCachedSize(cached_size);
  return total_size;
}

void NBestSentencePieceText::CheckTypeAndMergeFrom(
    const ::PROTOBUF_NAMESPACE_ID::MessageLite& from) {
  MergeFrom(*::PROTOBUF_NAMESPACE_ID::internal::DownCast<const NBestSentencePieceText*>(
      &from));
}

void NBestSentencePieceText::MergeFrom(const NBestSentencePieceText& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:sentencepiece.NBestSentencePieceText)
  GOOGLE_DCHECK_NE(&from, this);
  _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  (void) cached_has_bits;

  nbests_.MergeFrom(from.nbests_);
}

void NBestSentencePieceText::CopyFrom(const NBestSentencePieceText& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:sentencepiece.NBestSentencePieceText)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool NBestSentencePieceText::IsInitialized() const {
  if (!::PROTOBUF_NAMESPACE_ID::internal::AllAreInitialized(nbests_)) return false;
  return true;
}

void NBestSentencePieceText::InternalSwap(NBestSentencePieceText* other) {
  using std::swap;
  _internal_metadata_.Swap<std::string>(&other->_internal_metadata_);
  nbests_.InternalSwap(&other->nbests_);
}

std::string NBestSentencePieceText::GetTypeName() const {
  return "sentencepiece.NBestSentencePieceText";
}


// @@protoc_insertion_point(namespace_scope)
}  // namespace sentencepiece
PROTOBUF_NAMESPACE_OPEN
template<> PROTOBUF_NOINLINE ::sentencepiece::SentencePieceText_SentencePiece* Arena::CreateMaybeMessage< ::sentencepiece::SentencePieceText_SentencePiece >(Arena* arena) {
  return Arena::CreateMessageInternal< ::sentencepiece::SentencePieceText_SentencePiece >(arena);
}
template<> PROTOBUF_NOINLINE ::sentencepiece::SentencePieceText* Arena::CreateMaybeMessage< ::sentencepiece::SentencePieceText >(Arena* arena) {
  return Arena::CreateMessageInternal< ::sentencepiece::SentencePieceText >(arena);
}
template<> PROTOBUF_NOINLINE ::sentencepiece::NBestSentencePieceText* Arena::CreateMaybeMessage< ::sentencepiece::NBestSentencePieceText >(Arena* arena) {
  return Arena::CreateMessageInternal< ::sentencepiece::NBestSentencePieceText >(arena);
}
PROTOBUF_NAMESPACE_CLOSE

// @@protoc_insertion_point(global_scope)
#include <google/protobuf/port_undef.inc>
